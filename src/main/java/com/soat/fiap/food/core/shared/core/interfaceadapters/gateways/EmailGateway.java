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
	 * @param subject
	 *            assunto do e-mail
	 * @param body
	 *            corpo do e-mail (pode ser HTML)
	 */
	public void sendEmailToAuthenticatedUser(String subject, String body) {
		log.debug("Enviando e-mail ao usuário autenticado com assunto: {}", subject);
		emailDataSource.sendEmailToAuthenticatedUser(subject, body);
	}
}
