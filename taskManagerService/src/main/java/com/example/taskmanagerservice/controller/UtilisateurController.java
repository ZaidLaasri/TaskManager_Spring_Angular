package com.example.taskmanagerservice.controller;


import com.example.taskmanagerservice.entity.Utilisateur;
import com.example.taskmanagerservice.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/utilisateur")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;
    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService){
        this.utilisateurService=utilisateurService;
    }


    @GetMapping("/utilisateurs")
    public ResponseEntity<List<Utilisateur>> getUtilisateurs(){
        try{
        List<Utilisateur> utilisateurs = utilisateurService.getAllUtilisateurs();
        return new ResponseEntity<>(utilisateurs, HttpStatus.OK);
    }catch (RuntimeException e){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/utilisateur/{id}")
    public ResponseEntity<Utilisateur> getUtilisateur(@PathVariable Long id){
        try{
            Utilisateur utilisateur = utilisateurService.getUtilisateur(id);
            return new ResponseEntity<>(utilisateur,HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/utilisateur/{id}")
    public ResponseEntity<Utilisateur> supprimerUtilisateur(@PathVariable Long id){
        try{
            utilisateurService.supprimerUtilisateur(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }





}
