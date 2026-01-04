package com.soat.fiap.food.core.shared.infrastructure.common.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.*;

/**
 * Classe de configuração responsável por criar e configurar Bens de
 * serialização/desserialização
 *
 **/
@Configuration
public class Serializable {

	/**
	 * Cria e configura uma instância de {@link Gson} com adaptadores personalizados
	 * para manipulação de objetos {@link LocalDateTime}.
	 *
	 * @return uma instância configurada de {@link Gson}.
	 */
	@Bean
	public Gson gson() {
		return new GsonBuilder()
				.registerTypeAdapter(LocalDateTime.class,
						(JsonSerializer<LocalDateTime>) (src, typeOfSrc,
								context) -> new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
				.registerTypeAdapter(LocalDateTime.class,
						(JsonDeserializer<LocalDateTime>) (json, typeOfT, context) -> LocalDateTime
								.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME))
				.create();
	}

}
