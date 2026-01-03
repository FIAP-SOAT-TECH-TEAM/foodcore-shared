package com.soat.fiap.food.core.shared.core.interfaceadapters.gateways;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.soat.fiap.food.core.shared.infrastructure.common.source.EmailDataSource;

@ExtendWith(MockitoExtension.class)
class EmailGatewayTest {

	@Mock
	private EmailDataSource emailDataSource;

	@InjectMocks
	private EmailGateway gateway;

	@Test @DisplayName("Deve enviar email chamando EmailDataSource")
	void shouldSendEmail() {
		gateway.sendEmail("dest@email.com", "Assunto", "Conteúdo");

		verify(emailDataSource).sendEmail("dest@email.com", "Assunto", "Conteúdo");
	}
}
