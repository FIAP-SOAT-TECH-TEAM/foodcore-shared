package com.soat.fiap.food.core.shared.core.interfaceadapters.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserDTOTest {

	@Test @DisplayName("Deve permitir setar e obter nome e email")
	void shouldSetAndGetFields() {
		UserDTO user = new UserDTO();

		user.setName("João");
		user.setEmail("joao@email.com");

		assertEquals("João", user.getName());
		assertEquals("joao@email.com", user.getEmail());
	}

	@Test @DisplayName("Dois UserDTOs com mesmos dados devem ser iguais")
	void shouldBeEqualWhenFieldsAreEqual() {
		UserDTO user1 = new UserDTO();
		user1.setName("Maria");
		user1.setEmail("maria@email.com");

		UserDTO user2 = new UserDTO();
		user2.setName("Maria");
		user2.setEmail("maria@email.com");

		assertEquals(user1, user2);
		assertEquals(user1.hashCode(), user2.hashCode());
	}

	@Test @DisplayName("Dois UserDTOs com dados diferentes não devem ser iguais")
	void shouldNotBeEqualWhenFieldsAreDifferent() {
		UserDTO user1 = new UserDTO();
		user1.setName("Maria");
		user1.setEmail("maria@email.com");

		UserDTO user2 = new UserDTO();
		user2.setName("Ana");
		user2.setEmail("ana@email.com");

		assertNotEquals(user1, user2);
	}

	@Test @DisplayName("toString deve conter nome e email")
	void toStringShouldContainFields() {
		UserDTO user = new UserDTO();
		user.setName("Carlos");
		user.setEmail("carlos@email.com");

		String result = user.toString();

		assertNotNull(result);
		assertTrue(result.contains("Carlos"));
		assertTrue(result.contains("carlos@email.com"));
	}
}
