package com.cydeo.service.impl;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.KeycloakService;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class KeycloakServiceImpl implements KeycloakService {

    private final Keycloak keycloak;

    public KeycloakServiceImpl(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    @Override
    public Keycloak getKeycloakAdmin() {
        return keycloak;
    }

    @Override
    public void createKeycloakUser(UserDTO dto) {

        CredentialRepresentation credentials = new CredentialRepresentation();
        credentials.setTemporary(false);
        credentials.setType(CredentialRepresentation.PASSWORD);
        credentials.setValue(dto.getPassWord());
        UserRepresentation user = new UserRepresentation();
        user.setUsername(dto.getUserName());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setCredentials(Collections.singletonList(credentials));
        user.setEmailVerified(true);
        user.setEnabled(true);
        user.setRealmRoles(Collections.singletonList(dto.getRole().getDescription()));


        var x = keycloak.realm("cydeo-dev").users().create(user);
    }

    @Override
    public String getLoggedInUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> attributes = ((JwtAuthenticationToken) authentication).getTokenAttributes();
        return (String) attributes.get("preferred_username");
    }
}
