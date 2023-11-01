package com.cydeo.service;

import com.cydeo.dto.UserDTO;
import org.keycloak.admin.client.Keycloak;

public interface KeycloakService {

    Keycloak getKeycloakAdmin();

    void createKeycloakUser(UserDTO user);

    String getLoggedInUserName();
}
