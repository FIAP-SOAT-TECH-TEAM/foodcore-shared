package com.soat.fiap.food.core.shared.core.domain.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResourceConflictExceptionTest {

	@Test
	@DisplayName("Deve criar ResourceConflictException com mensagem direta")
	void shouldCreateWithCustomMessage() {
		String message = "CPF já existente";

		ResourceConflictException exception = new ResourceConflictException(message);

		assertEquals(message, exception.getMessage());
	}

	@Test
	@DisplayName("Deve criar ResourceConflictException com mensagem formatada")
	void shouldCreateWithFormattedMessage() {
		ResourceConflictException exception =
				new ResourceConflictException("Usuário", "CPF", "12345678900");

		assertEquals(
				"Já existe um(a) Usuário com CPF: 12345678900",
				exception.getMessage()
		);
	}
}
