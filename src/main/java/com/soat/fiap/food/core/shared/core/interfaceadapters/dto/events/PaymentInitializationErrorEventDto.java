package com.soat.fiap.food.core.shared.core.interfaceadapters.dto.events;

import com.soat.fiap.food.core.shared.payment.core.domain.events.PaymentInitializationErrorEvent;

import lombok.Data;

/**
 * DTO utilizado para representar dados do evento de domínio
 * {@link PaymentInitializationErrorEvent}. Serve como objeto de transferência
 * entre o domínio e o mundo externo (DataSource).
 */
@Data
public class PaymentInitializationErrorEventDto {
	public Long orderId;
	public String errorMessage;
}
