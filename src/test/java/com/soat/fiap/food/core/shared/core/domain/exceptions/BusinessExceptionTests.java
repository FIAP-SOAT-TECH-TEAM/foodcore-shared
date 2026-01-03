package com.soat.fiap.food.core.shared.core.domain.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BusinessExceptionTest {

	@Test
	@DisplayName("Deve criar BusinessException com mensagem")
	void shouldCreateBusinessExceptionWithMessage() {
		String message = "Regra de negócio violada";

		BusinessException exception = new BusinessException(message);

		assertEquals(message, exception.getMessage());
		assertNull(exception.getCause());
	}

	@Test
	@DisplayName("Deve criar BusinessException com mensagem e causa")
	void shouldCreateBusinessExceptionWithMessageAndCause() {
		String message = "Erro de negócio";
		Throwable cause = new IllegalStateException("Causa raiz");

		BusinessException exception = new BusinessException(message, cause);

		assertEquals(message, exception.getMessage());
		assertEquals(cause, exception.getCause());
	}
}
