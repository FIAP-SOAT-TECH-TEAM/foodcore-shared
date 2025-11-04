package com.soat.fiap.food.core.shared.infrastructure.common.source;

import com.soat.fiap.food.core.shared.core.interfaceadapters.dto.UserDTO;

/**
 * Fornece informações de um usuário.
 * <p>
 * Esta interface permite recuperar informações de um usuário, via Amazon
 * Cognito ou outro provedor.
 * </p>
 */
public interface UserSource {

	/**
	 * Recupera um usuário a partir de seu identificador único.
	 *
	 * @param id
	 *            Identificador do usuário
	 * @return Um {@link UserDTO} contendo informações do usuário.
	 */
	public UserDTO getUserById(String id);

}
