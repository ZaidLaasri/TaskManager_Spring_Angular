package com.example.taskmanagerservice.Mapper;

import com.example.taskmanagerservice.DTO.TacheDTO;
import com.example.taskmanagerservice.entity.Tache;
import com.example.taskmanagerservice.entity.Utilisateur;
import org.springframework.stereotype.Component;

@Component
public class TacheMapper {
    public TacheDTO toDTO(Tache tache){
        TacheDTO tacheDTO = new TacheDTO();
        tacheDTO.setId(tache.getId());
        tacheDTO.setEstTermine(tache.getEstTermine());
        tacheDTO.setDateEcheance(tache.getDateEcheance());
        tacheDTO.setDescription(tache.getDescription());
        tacheDTO.setUtilisateur(tache.getUtilisateur().getId());

        return tacheDTO;
    }

    public  Tache toEntity(TacheDTO tacheDTO, Utilisateur utilisateur){
        Tache tache = new Tache();
        tache.setId(tacheDTO.getId());
        tache.setEstTermine(tacheDTO.getEstTermine());
        tache.setDateEcheance(tacheDTO.getDateEcheance());
        tache.setDescription(tacheDTO.getDescription());
        tache.setUtilisateur(utilisateur);

        return tache;

    }


}
