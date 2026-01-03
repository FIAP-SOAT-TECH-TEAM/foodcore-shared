package com.soat.fiap.food.core.shared.core.interfaceadapters.gateways;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.soat.fiap.food.core.shared.core.interfaceadapters.dto.FileUploadDTO;
import com.soat.fiap.food.core.shared.infrastructure.common.source.ImageDataSource;

@ExtendWith(MockitoExtension.class)
class ImageStorageGatewayTest {

	@Mock
	private ImageDataSource imageDataSource;

	@InjectMocks
	private ImageStorageGateway gateway;

	@Test
	@DisplayName("Deve fazer upload da imagem e retornar URL")
	void shouldUploadImage() {
		FileUploadDTO file = mock(FileUploadDTO.class);
		String path = "catalogs/1/categories/5";
		String expectedUrl = "https://blob/image.png";

		when(imageDataSource.uploadImage(path, file)).thenReturn(expectedUrl);

		String url = gateway.uploadImage(path, file);

		assertEquals(expectedUrl, url);
		verify(imageDataSource).uploadImage(path, file);
	}

	@Test
	@DisplayName("Deve deletar imagem chamando ImageDataSource")
	void shouldDeleteImage() {
		String imageUrl = "https://blob/image.png";

		gateway.deleteImage(imageUrl);

		verify(imageDataSource).deleteImage(imageUrl);
	}
}
