package com.soat.fiap.food.core.shared.infrastructure.common.source;

import com.soat.fiap.food.core.shared.infrastructure.in.web.api.auth.exceptions.AccessDeniedException;

/**
 * Gerenciador de acesso para autenticação e autorização do usuário.
 */
public interface AccessManagerSource {

	/**
	 * Valida se o usuário autenticado tem permissão para acessar os dados de um
	 * usuário específico.
	 *
	 * @param userId
	 *            ID do usuário relacionado ao recurso sendo acessado.
	 * @throws AccessDeniedException
	 *             se o acesso não for permitido.
	 */
	void validateAccess(String userId);
}
