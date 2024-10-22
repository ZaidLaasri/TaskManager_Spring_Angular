package com.example.taskmanagerservice.security.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    private String email;
    private String  motDePasse;
}
