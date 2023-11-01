package com.cydeo.config;

import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {

    private final KeycloakProperties keycloakProperties;

    public KeycloakConfig(KeycloakProperties keycloakProperties) {
        this.keycloakProperties = keycloakProperties;
    }

    @Bean
    Keycloak keycloakadmin(){

        return KeycloakBuilder.builder()
                .serverUrl(keycloakProperties.getAuthServerUrl())
                .grantType(OAuth2Constants.PASSWORD)
                .realm(keycloakProperties.getRealm())
                .username(keycloakProperties.getMasterUser())
                .password(keycloakProperties.getMasterUserPswd())
                .clientId(keycloakProperties.getMasterClient())
                .resteasyClient(new ResteasyClientBuilderImpl().connectionPoolSize(10).build())
                .build();
    }
}
