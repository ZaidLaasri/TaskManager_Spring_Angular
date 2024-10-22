package com.example.taskmanagerservice.controller;


import com.example.taskmanagerservice.entity.Utilisateur;
import com.example.taskmanagerservice.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private UtilisateurService utilisateurService;

    @Autowired
    public RegisterController(UtilisateurService utilisateurService){
        this.utilisateurService=utilisateurService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Utilisateur utilisateur){
        if(utilisateurService.getUtilisateurByEmail(utilisateur.getEmail())!=null){
            return ResponseEntity.badRequest().body("L'email est déjà utilisé.");
        }else {
            utilisateurService.ajouterUtilisateur(utilisateur);
            return ResponseEntity.ok("utilisateur enregistré avecsuccès !");
        }
    }

}
