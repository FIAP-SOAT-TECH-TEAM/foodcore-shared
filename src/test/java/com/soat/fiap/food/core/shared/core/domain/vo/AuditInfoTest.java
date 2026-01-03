package com.soat.fiap.food.core.shared.core.domain.vo;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.soat.fiap.food.core.shared.core.domain.exceptions.BusinessException;

class AuditInfoTest {

	@Test @DisplayName("Deve criar AuditInfo com datas automáticas no construtor default")
	void shouldCreateAuditInfoWithDefaultConstructor() {
		AuditInfo auditInfo = new AuditInfo();

		assertNotNull(auditInfo.getCreatedAt());
		assertNotNull(auditInfo.getUpdatedAt());
		assertTrue(auditInfo.getUpdatedAt().isAfter(auditInfo.getCreatedAt()));
	}

	@Test @DisplayName("Deve criar AuditInfo com datas válidas")
	void shouldCreateAuditInfoWithValidDates() {
		LocalDateTime createdAt = LocalDateTime.now();
		LocalDateTime updatedAt = createdAt.plusMinutes(1);

		AuditInfo auditInfo = new AuditInfo(createdAt, updatedAt);

		assertEquals(createdAt, auditInfo.getCreatedAt());
		assertEquals(updatedAt, auditInfo.getUpdatedAt());
	}

	@Test @DisplayName("Não deve permitir updatedAt nulo")
	void shouldThrowExceptionWhenUpdatedAtIsNull() {
		LocalDateTime createdAt = LocalDateTime.now();

		assertThrows(NullPointerException.class, () -> new AuditInfo(createdAt, null));
	}

	@Test @DisplayName("Não deve permitir updatedAt antes de createdAt")
	void shouldThrowBusinessExceptionWhenUpdatedAtIsBeforeCreatedAt() {
		LocalDateTime createdAt = LocalDateTime.now();
		LocalDateTime updatedAt = createdAt.minusMinutes(1);

		BusinessException exception = assertThrows(BusinessException.class, () -> new AuditInfo(createdAt, updatedAt));

		assertEquals("UpdatedAt não pode ser menor ou igual a CreatedAt", exception.getMessage());
	}

	@Test @DisplayName("Quando updatedAt for igual a createdAt, deve ajustar automaticamente")
	void shouldAdjustUpdatedAtWhenEqualToCreatedAt() {
		LocalDateTime now = LocalDateTime.now();

		AuditInfo auditInfo = new AuditInfo(now, now);

		assertTrue(auditInfo.getUpdatedAt().isAfter(auditInfo.getCreatedAt()));
	}

	@Test @DisplayName("Deve permitir atualizar updatedAt para uma data válida")
	void shouldUpdateUpdatedAtSuccessfully() {
		LocalDateTime createdAt = LocalDateTime.now();
		LocalDateTime initialUpdatedAt = createdAt.plusMinutes(1);
		LocalDateTime newUpdatedAt = createdAt.plusMinutes(5);

		AuditInfo auditInfo = new AuditInfo(createdAt, initialUpdatedAt);
		auditInfo.setUpdatedAt(newUpdatedAt);

		assertEquals(newUpdatedAt, auditInfo.getUpdatedAt());
	}
}
