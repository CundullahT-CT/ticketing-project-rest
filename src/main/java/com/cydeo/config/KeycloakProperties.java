package com.cydeo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class KeycloakProperties {

    @Value("${master.realm}")
    private String realm;

    @Value("${keycloak.realm}")
    private String keycloakRealm;

    @Value("${keycloak.auth-server-url}")
    private String authServerUrl;

    @Value("${master.client}")
    private String masterClient;

    @Value("${keycloak.clientId}")
    private String clientId;

    @Value("${keycloak.client.secret}")
    private String clientSecret;

    @Value("${master.user}")
    private String masterUser;

    @Value("${master.user.password}")
    private String masterUserPswd;

}