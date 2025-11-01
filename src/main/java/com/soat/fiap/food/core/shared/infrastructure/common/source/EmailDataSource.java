package com.soat.fiap.food.core.shared.infrastructure.common.source;

/**
 * Fonte de dados responsável pelo envio de e-mails.
 */
public interface EmailDataSource {

	/**
	 * Envia um e-mail simples (texto ou HTML) para o usuário autenticado.
	 *
	 * @param subject
	 *            o assunto do e-mail
	 * @param body
	 *            o corpo do e-mail (pode ser HTML)
	 * @throws RuntimeException
	 *             se ocorrer erro ao enviar o e-mail
	 */
	void sendEmailToAuthenticatedUser(String subject, String body);
}
