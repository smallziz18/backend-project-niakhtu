package com.niakhtu.backend.niakhtu.models;

import lombok.Data;

@Data // Génère automatiquement les getters, setters, toString, equals, et hashCode
public class LoginRequest {
    private String pseudo;
    private String password;
}
