package com.soat.fiap.food.core.shared.core.interfaceadapters.dto.events;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.soat.fiap.food.core.shared.catalog.core.domain.events.ProductCreatedEvent;

import lombok.Data;

/**
 * DTO utilizado para representar dados do evento de domínio
 * {@link ProductCreatedEvent}. Serve como objeto de transferência entre o
 * domínio e o mundo externo (DataSource).
 */
@Data
public class ProductCreatedEventDto {
	public Long productId;
	public String productName;
	public BigDecimal price;
	public Long categoryId;
	public LocalDateTime timestamp;
}
