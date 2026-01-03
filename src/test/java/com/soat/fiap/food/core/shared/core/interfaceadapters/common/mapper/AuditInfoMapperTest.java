package com.soat.fiap.food.core.shared.infrastructure.common.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.soat.fiap.food.core.shared.core.domain.vo.AuditInfo;

class AuditInfoMapperTest {

	@Test
	@DisplayName("Deve criar AuditInfo com datas informadas")
	void shouldBuildAuditInfoWithDates() {
		LocalDateTime createdAt = LocalDateTime.now();
		LocalDateTime updatedAt = createdAt.plusMinutes(1);

		AuditInfo auditInfo = AuditInfoMapper.buildAuditInfo(createdAt, updatedAt);

		assertNotNull(auditInfo);
		assertEquals(createdAt, auditInfo.getCreatedAt());
		assertEquals(updatedAt, auditInfo.getUpdatedAt());
	}

	@Test
	@DisplayName("Deve criar AuditInfo default quando createdAt for nulo")
	void shouldBuildDefaultAuditInfoWhenCreatedAtIsNull() {
		AuditInfo auditInfo = AuditInfoMapper.buildAuditInfo(null, LocalDateTime.now());

		assertNotNull(auditInfo);
		assertNotNull(auditInfo.getCreatedAt());
		assertNotNull(auditInfo.getUpdatedAt());
	}

	@Test
	@DisplayName("Deve criar AuditInfo default quando updatedAt for nulo")
	void shouldBuildDefaultAuditInfoWhenUpdatedAtIsNull() {
		AuditInfo auditInfo = AuditInfoMapper.buildAuditInfo(LocalDateTime.now(), null);

		assertNotNull(auditInfo);
		assertNotNull(auditInfo.getCreatedAt());
		assertNotNull(auditInfo.getUpdatedAt());
	}

	@Test
	@DisplayName("Deve mapear createdAt corretamente")
	void shouldMapCreatedAt() {
		LocalDateTime createdAt = LocalDateTime.now();
		AuditInfo auditInfo = new AuditInfo(createdAt, createdAt.plusMinutes(1));

		AuditInfoMapper mapper = new AuditInfoMapper() {};
		LocalDateTime result = mapper.mapCreatedAt(auditInfo);

		assertEquals(createdAt, result);
	}

	@Test
	@DisplayName("Deve retornar null ao mapear createdAt com AuditInfo nulo")
	void shouldReturnNullWhenMappingCreatedAtWithNullAuditInfo() {
		AuditInfoMapper mapper = new AuditInfoMapper() {};

		assertNull(mapper.mapCreatedAt(null));
	}

	@Test
	@DisplayName("Deve mapear updatedAt corretamente")
	void shouldMapUpdatedAt() {
		LocalDateTime createdAt = LocalDateTime.now();
		LocalDateTime updatedAt = createdAt.plusMinutes(2);
		AuditInfo auditInfo = new AuditInfo(createdAt, updatedAt);

		AuditInfoMapper mapper = new AuditInfoMapper() {};
		LocalDateTime result = mapper.mapUpdatedAt(auditInfo);

		assertEquals(updatedAt, result);
	}

	@Test
	@DisplayName("Deve retornar null ao mapear updatedAt com AuditInfo nulo")
	void shouldReturnNullWhenMappingUpdatedAtWithNullAuditInfo() {
		AuditInfoMapper mapper = new AuditInfoMapper() {};

		assertNull(mapper.mapUpdatedAt(null));
	}
}
