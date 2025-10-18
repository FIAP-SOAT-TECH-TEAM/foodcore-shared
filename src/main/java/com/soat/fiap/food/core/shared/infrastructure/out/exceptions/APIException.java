package com.soat.fiap.food.core.shared.infrastructure.out.exceptions;

import lombok.Getter;

/**
 * Exceção lançada quando existe um erro de retorno de uma API
 */
@Getter
public class APIException extends RuntimeException {

	private final int statusCode;

	public APIException(String message, int statusCode) {
		super(message);
		this.statusCode = statusCode;
	}

	public APIException(String message, Throwable cause, int statusCode) {
		super(message, cause);
		this.statusCode = statusCode;
	}

}
