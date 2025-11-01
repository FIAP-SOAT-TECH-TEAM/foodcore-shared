package com.soat.fiap.food.core.shared.infrastructure.out.email;

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
@Component @RequiredArgsConstructor @Slf4j
public class DefaultEmailDataSource implements EmailDataSource {

	private final JavaMailSender mailSender;
	private final AuthenticatedUserSource authenticatedUserSource;

	@Override
	public void sendEmailToAuthenticatedUser(String subject, String body) {
		String recipient = authenticatedUserSource.getEmail();

		if (recipient == null || recipient.isBlank()) {
			log.info("Usuário autenticado não possui e-mail válido");
			return;
		}

		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

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
