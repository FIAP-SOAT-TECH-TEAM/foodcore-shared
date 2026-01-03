package com.soat.fiap.food.core.shared.core.interfaceadapters.gateways;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.soat.fiap.food.core.shared.infrastructure.common.source.AccessManagerSource;
import com.soat.fiap.food.core.shared.infrastructure.in.web.api.auth.exceptions.AccessDeniedException;

@ExtendWith(MockitoExtension.class)
class AccessManagerGatewayTest {

	@Mock
	private AccessManagerSource accessManagerSource;

	@InjectMocks
	private AccessManagerGateway gateway;

	@Test @DisplayName("Deve validar acesso chamando o AccessManagerSource")
	void shouldValidateAccessSuccessfully() {
		String userId = "123";

		assertDoesNotThrow(() -> gateway.validateAccess(userId));

		verify(accessManagerSource).validateAccess(userId);
	}

	@Test @DisplayName("Deve propagar AccessDeniedException")
	void shouldPropagateAccessDeniedException() {
		String userId = "123";

		doThrow(new AccessDeniedException("Acesso negado")).when(accessManagerSource).validateAccess(userId);

		assertThrows(AccessDeniedException.class, () -> gateway.validateAccess(userId));
	}
}
