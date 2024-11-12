package com.example.taskmanagerservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UtilisateurDTO {
    private Long id;
    private String nom;
    private String email;
    private String motDePasse;
}
