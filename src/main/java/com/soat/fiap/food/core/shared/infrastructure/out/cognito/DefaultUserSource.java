package com.soat.fiap.food.core.shared.infrastructure.out.cognito;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.soat.fiap.food.core.shared.core.interfaceadapters.dto.UserDTO;
import com.soat.fiap.food.core.shared.infrastructure.common.source.UserSource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CognitoIdentityProviderException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ListUsersRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ListUsersResponse;

@Slf4j @Component @Configuration @ConditionalOnProperty(prefix = "aws.cognito", name = "userPoolId")
@RequiredArgsConstructor
public class DefaultUserSource implements UserSource {

	private final CognitoIdentityProviderClient cognitoClient;

	@Value("${aws.cognito.userPoolId}")
	private String userPoolId;

	@Override
	public UserDTO getUserById(String subject) {
		try {
			log.info("Consultando usuário Cognito pelo subject: {}", subject);

			ListUsersRequest request = ListUsersRequest.builder()
					.userPoolId(userPoolId)
					.filter(String.format("sub = \"%s\"", subject))
					.limit(1)
					.build();

			ListUsersResponse response = cognitoClient.listUsers(request);

			if (response.users().isEmpty()) {
				log.warn("Nenhum usuário encontrado com subject: {}", subject);
				return null;
			}

			var cognitoUser = response.users().getFirst();
			UserDTO dto = new UserDTO();

			cognitoUser.attributes().forEach(attr -> {
				switch (attr.name()) {
					case "email" -> dto.setEmail(attr.value());
					case "name" -> dto.setName(attr.value());
				}
			});

			log.info("Usuário Cognito recuperado pelo subject: {}", dto);
			return dto;

		} catch (CognitoIdentityProviderException e) {
			log.error("Erro ao recuperar usuário com subject {}: {}", subject, e.awsErrorDetails().errorMessage());
			throw new IllegalStateException("Erro ao consultar usuário no Cognito", e);
		}
	}
}
