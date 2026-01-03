package com.soat.fiap.food.core.shared.core.domain.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResourceNotFoundExceptionTest {

	@Test
	@DisplayName("Deve criar ResourceNotFoundException com mensagem direta")
	void shouldCreateWithCustomMessage() {
		String message = "Pedido não encontrado";

		ResourceNotFoundException exception = new ResourceNotFoundException(message);

		assertEquals(message, exception.getMessage());
	}

	@Test
	@DisplayName("Deve criar ResourceNotFoundException com resource, campo e valor")
	void shouldCreateWithResourceAndField() {
		ResourceNotFoundException exception =
				new ResourceNotFoundException("Usuário", "email", "teste@email.com");

		assertEquals(
				"Usuário não encontrado com email: teste@email.com",
				exception.getMessage()
		);
	}

	@Test
	@DisplayName("Deve criar ResourceNotFoundException com resource e id")
	void shouldCreateWithResourceAndId() {
		ResourceNotFoundException exception =
				new ResourceNotFoundException("Pedido", 10L);

		assertEquals(
				"Pedido não encontrado com id: 10",
				exception.getMessage()
		);
	}
}
