package com.soat.fiap.food.core.shared.infrastructure.common.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CycleAvoidingMappingContextTest {

	@Test @DisplayName("Deve armazenar e recuperar instância mapeada")
	void shouldStoreAndRetrieveMappedInstance() {
		CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();

		Object source = new Object();
		String target = "mapped";

		context.storeMappedInstance(source, target);

		String result = context.getMappedInstance(source, String.class);

		assertEquals(target, result);
	}

	@Test @DisplayName("Deve retornar null quando não existir instância mapeada")
	void shouldReturnNullWhenNoMappedInstanceExists() {
		CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();

		Object source = new Object();

		Object result = context.getMappedInstance(source, Object.class);

		assertNull(result);
	}

	@Test @DisplayName("Deve usar identidade e não equals para armazenar instâncias")
	void shouldUseIdentityHashMap() {
		CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();

		String source1 = new String("test");
		String source2 = new String("test"); // equals true, identidade diferente

		context.storeMappedInstance(source1, "mapped1");

		assertNull(context.getMappedInstance(source2, String.class));
		assertEquals("mapped1", context.getMappedInstance(source1, String.class));
	}
}
