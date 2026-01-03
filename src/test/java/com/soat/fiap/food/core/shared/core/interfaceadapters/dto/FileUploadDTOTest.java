package com.soat.fiap.food.core.shared.core.interfaceadapters.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileUploadDTOTest {

	@Test @DisplayName("Deve considerar dois DTOs iguais quando fileName e content forem iguais")
	void shouldBeEqualWhenFileNameAndContentAreEqual() {
		byte[] content1 = {1, 2, 3};
		byte[] content2 = {1, 2, 3};

		FileUploadDTO dto1 = new FileUploadDTO("image.png", content1);
		FileUploadDTO dto2 = new FileUploadDTO("image.png", content2);

		assertEquals(dto1, dto2);
		assertEquals(dto1.hashCode(), dto2.hashCode());
	}

	@Test @DisplayName("Não deve ser igual quando fileName for diferente")
	void shouldNotBeEqualWhenFileNameIsDifferent() {
		byte[] content = {1, 2, 3};

		FileUploadDTO dto1 = new FileUploadDTO("image1.png", content);
		FileUploadDTO dto2 = new FileUploadDTO("image2.png", content);

		assertNotEquals(dto1, dto2);
	}

	@Test @DisplayName("Não deve ser igual quando content for diferente")
	void shouldNotBeEqualWhenContentIsDifferent() {
		FileUploadDTO dto1 = new FileUploadDTO("image.png", new byte[]{1, 2, 3});
		FileUploadDTO dto2 = new FileUploadDTO("image.png", new byte[]{4, 5, 6});

		assertNotEquals(dto1, dto2);
	}

	@Test @DisplayName("Deve retornar false ao comparar com null ou outro tipo")
	void shouldReturnFalseWhenComparingWithNullOrDifferentType() {
		FileUploadDTO dto = new FileUploadDTO("image.png", new byte[]{1});

		assertNotEquals(dto, null);
		assertNotEquals(dto, "string");
	}

	@Test @DisplayName("toString deve conter fileName e conteúdo")
	void toStringShouldContainFields() {
		FileUploadDTO dto = new FileUploadDTO("image.png", new byte[]{1, 2});

		String result = dto.toString();

		assertNotNull(result);
		assertTrue(result.contains("image.png"));
		assertTrue(result.contains("1"));
		assertTrue(result.contains("2"));
	}
}
