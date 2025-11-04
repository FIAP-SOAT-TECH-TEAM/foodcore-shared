package com.soat.fiap.food.core.shared.core.interfaceadapters.gateways;

import com.soat.fiap.food.core.shared.infrastructure.common.source.EmailDataSource;

import lombok.extern.slf4j.Slf4j;

/**
 * Gateway para envio de e-mails.
 */
@Slf4j
public class EmailGateway {

	private final EmailDataSource emailDataSource;

	public EmailGateway(EmailDataSource emailDataSource) {
		this.emailDataSource = emailDataSource;
	}

	/**
	 * Envia um e-mail para o usuário autenticado.
	 *
	 * @param recipient
	 *            destinatário do e-mail.
	 * @param subject
	 *            o assunto do e-mail
	 * @param body
	 *            o corpo do e-mail (pode ser HTML)
	 */
	public void sendEmail(String recipient, String subject, String body) {
		log.debug("Enviando e-mail ao destinatário: {} com assunto: {}", recipient, subject);
		emailDataSource.sendEmail(recipient, subject, body);
	}
}
