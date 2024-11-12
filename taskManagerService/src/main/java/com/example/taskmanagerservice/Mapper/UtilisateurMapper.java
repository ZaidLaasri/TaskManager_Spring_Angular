package com.example.taskmanagerservice.Mapper;

import com.example.taskmanagerservice.DTO.UtilisateurDTO;
import com.example.taskmanagerservice.entity.Utilisateur;

public class UtilisateurMapper {

    public static UtilisateurDTO toDTO(Utilisateur utilisateur){
        UtilisateurDTO dto = new UtilisateurDTO();
        dto.setId(utilisateur.getId());
        dto.setNom(utilisateur.getNom());
        dto.setEmail(utilisateur.getEmail());
        dto.setMotDePasse(utilisateur.getMotDePasse());
        return dto;
    }


    public static Utilisateur toEntity(UtilisateurDTO dto){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(dto.getId());
        utilisateur.setNom(dto.getNom());
        utilisateur.setEmail(dto.getEmail());
        utilisateur.setMotDePasse(dto.getMotDePasse());

        return utilisateur;
    }
}
