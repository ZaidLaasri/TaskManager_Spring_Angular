package com.example.taskmanagerservice.service;


import com.example.taskmanagerservice.entity.Utilisateur;
import com.example.taskmanagerservice.repository.UtilisateurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {

    private static final Logger logger =  LoggerFactory.getLogger(UtilisateurService.class);

    private final UtilisateurRepository utilisateurRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder){
        this.utilisateurRepository=utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Utilisateur> getAllUtilisateurs(){
        logger.info("Récupération des utilisateurs");
        return utilisateurRepository.findAll();
    }

    public Utilisateur getUtilisateur(long id){
        logger.info("Récupération d'utilisateur par id : {}", id);

        return utilisateurRepository.findById(id).orElse(null);
    }

    public Utilisateur getUtilisateurByEmail(String email){
        logger.info("Récupération d'utilisateur par mail : {}", email);
        return utilisateurRepository.findByEmail(email).orElse(null);
    }

    public Utilisateur getUtilisateurByName(String nom){

        logger.info("Récupération d'utilisateur par nom : {}", nom);
        return utilisateurRepository.findByNom(nom).orElse(null);
    }

    public void supprimerUtilisateur (long id){
        logger.info("Suppression d'utilisateur  : {}", id);
        utilisateurRepository.deleteById(id);
    }

    public Utilisateur ajouterUtilisateur(Utilisateur utilisateur){
        logger.info("Ajout d'un nouvel utilisateur avec email : {}", utilisateur.getEmail());
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur updateUtilisateur(Long id, Utilisateur utilisateurDetail){
        Utilisateur utilisateurExistant = utilisateurRepository.findById(id).orElseThrow(()->new RuntimeException("Utilisateur non trouvé avec l'id : "+id));

       utilisateurExistant.setNom(utilisateurDetail.getNom());
       utilisateurExistant.setEmail(utilisateurDetail.getEmail());
            utilisateurExistant.setMotDePasse(passwordEncoder.encode(utilisateurDetail.getMotDePasse()));
       return utilisateurRepository.save(utilisateurExistant);
    }
    

}
