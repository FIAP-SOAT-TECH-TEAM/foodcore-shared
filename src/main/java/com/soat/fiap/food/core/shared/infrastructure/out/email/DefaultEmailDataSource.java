package com.soat.fiap.food.core.shared.infrastructure.out.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.soat.fiap.food.core.shared.infrastructure.common.source.AuthenticatedUserSource;
import com.soat.fiap.food.core.shared.infrastructure.common.source.EmailDataSource;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementação padrão de {@link EmailDataSource}, baseada em SMTP, responsável
 * por enviar e-mails para o usuário autenticado.
 */
@Component @RequiredArgsConstructor @ConditionalOnProperty(prefix = "spring.mail", name = "host") @Slf4j
public class DefaultEmailDataSource implements EmailDataSource {

	private final JavaMailSender mailSender;
	private final AuthenticatedUserSource authenticatedUserSource;
	@Value("${spring.mail.from}")
	private String from;

	@Override
	public void sendEmail(String recipient, String subject, String body) {

		if (recipient == null || recipient.isBlank()) {
			log.error("Não foi possível determinar o remetente do e-mail.");
			throw new IllegalArgumentException("Não foi possível determinar o remetente do e-mail.");
		}

		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

			if (from != null && !from.isEmpty())
				helper.setFrom(from);

			helper.setTo(recipient);
			helper.setSubject(subject);
			helper.setText(body, true);

			mailSender.send(message);

			log.info("E-mail enviado com sucesso para: {}", recipient);

		} catch (MessagingException e) {
			log.error("Erro ao enviar e-mail para {}: {}", recipient, e.getMessage(), e);
			throw new RuntimeException("Falha ao enviar e-mail", e);
		}
	}
}
