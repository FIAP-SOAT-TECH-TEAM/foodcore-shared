package com.soat.fiap.food.core.shared.core.interfaceadapters.gateways;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.OffsetDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.soat.fiap.food.core.shared.infrastructure.common.source.AuthenticatedUserSource;

@ExtendWith(MockitoExtension.class)
class AuthenticatedUserGatewayTest {

	@Mock
	private AuthenticatedUserSource authenticatedUserSource;

	@InjectMocks
	private AuthenticatedUserGateway gateway;

	@Test
	@DisplayName("Deve retornar subject do usuário autenticado")
	void shouldReturnSubject() {
		when(authenticatedUserSource.getSubject()).thenReturn("sub-123");

		String result = gateway.getSubject();

		assertEquals("sub-123", result);
		verify(authenticatedUserSource).getSubject();
	}

	@Test
	@DisplayName("Deve retornar nome do usuário")
	void shouldReturnName() {
		when(authenticatedUserSource.getName()).thenReturn("Fulano");

		assertEquals("Fulano", gateway.getName());
	}

	@Test
	@DisplayName("Deve retornar email do usuário")
	void shouldReturnEmail() {
		when(authenticatedUserSource.getEmail()).thenReturn("teste@email.com");

		assertEquals("teste@email.com", gateway.getEmail());
	}

	@Test
	@DisplayName("Deve retornar CPF do usuário")
	void shouldReturnCpf() {
		when(authenticatedUserSource.getCpf()).thenReturn("12345678900");

		assertEquals("12345678900", gateway.getCpf());
	}

	@Test
	@DisplayName("Deve retornar role do usuário")
	void shouldReturnRole() {
		when(authenticatedUserSource.getRole()).thenReturn("ADMIN");

		assertEquals("ADMIN", gateway.getRole());
	}

	@Test
	@DisplayName("Deve retornar data de criação do usuário")
	void shouldReturnCreationDate() {
		OffsetDateTime now = OffsetDateTime.now();
		when(authenticatedUserSource.getCreationDate()).thenReturn(now);

		assertEquals(now, gateway.getCreationDate());
	}
}
