package com.example.taskmanagerservice.service;


import com.example.taskmanagerservice.DTO.UtilisateurDTO;
import com.example.taskmanagerservice.Mapper.UtilisateurMapper;
import com.example.taskmanagerservice.entity.Utilisateur;
import com.example.taskmanagerservice.repository.UtilisateurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UtilisateurService {

    private static final Logger logger =  LoggerFactory.getLogger(UtilisateurService.class);

    private final UtilisateurRepository utilisateurRepository;

    private final PasswordEncoder passwordEncoder;

    private final UtilisateurMapper utilisateurMapper;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder, UtilisateurMapper utilisateurMapper){
        this.utilisateurRepository=utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
        this.utilisateurMapper = utilisateurMapper;
    }

    public List<UtilisateurDTO> getAllUtilisateurs(){
        logger.info("Récupération des utilisateurs");
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        return utilisateurs.stream().map(utilisateurMapper::toDTO).collect(Collectors.toList());
    }

    public UtilisateurDTO getUtilisateur(long id){
        logger.info("Récupération d'utilisateur par id : {}", id);

        Utilisateur utilisateur =utilisateurRepository.findById(id).orElse(null);
        return utilisateurMapper.toDTO(utilisateur);
    }

    public Utilisateur getUtilisateurByEmail(String email){
        logger.info("Récupération d'utilisateur par mail : {}", email);
        return utilisateurRepository.findByEmail(email).orElse(null);
    }



    public void supprimerUtilisateur (long id){
        logger.info("Suppression d'utilisateur  : {}", id);
        utilisateurRepository.deleteById(id);
    }

    public UtilisateurDTO ajouterUtilisateur(UtilisateurDTO utilisateur){
        logger.info("Ajout d'un nouvel utilisateur avec email : {}", utilisateur.getEmail());
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
        Utilisateur newUtilisateur = utilisateurRepository.save(utilisateurMapper.toEntity(utilisateur));
        return utilisateurMapper.toDTO(newUtilisateur);
    }

    public UtilisateurDTO updateUtilisateur(Long id, UtilisateurDTO utilisateurDetail){
        Utilisateur utilisateurExistant = utilisateurRepository.findById(id).orElseThrow(()->new RuntimeException("Utilisateur non trouvé avec l'id : "+id));

       utilisateurExistant.setNom(utilisateurDetail.getNom());
       utilisateurExistant.setEmail(utilisateurDetail.getEmail());
       utilisateurExistant.setMotDePasse(passwordEncoder.encode(utilisateurDetail.getMotDePasse()));
       Utilisateur newUtilisateur = utilisateurRepository.save(utilisateurExistant);

       return utilisateurMapper.toDTO(newUtilisateur);
    }
    

}
