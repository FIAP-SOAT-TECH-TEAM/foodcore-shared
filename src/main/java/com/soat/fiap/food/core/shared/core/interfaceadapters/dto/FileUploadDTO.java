package com.soat.fiap.food.core.shared.core.interfaceadapters.dto;

import java.util.Arrays;
import java.util.Objects;

/**
 * DTO utilizado para representar dados básicos para upload de imagens. Serve
 * como objeto de transferência entre o domínio e o mundo externo (DataSource).
 */
public record FileUploadDTO(String fileName, byte[] content) {

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof FileUploadDTO))
			return false;
		FileUploadDTO that = (FileUploadDTO) o;
		return Objects.equals(fileName, that.fileName) && Arrays.equals(content, that.content);
	}

	@Override
	public int hashCode() {
		return Objects.hash(fileName, Arrays.hashCode(content));
	}

	@Override
	public String toString() {
		return "FileUploadDTO{" + "fileName=" + fileName + "\\" + ", content=" + Arrays.toString(content) + '}';
	}
}
