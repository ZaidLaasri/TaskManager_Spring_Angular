package com.example.taskmanagerservice.controller;

import com.example.taskmanagerservice.entity.Tache;
import com.example.taskmanagerservice.entity.Utilisateur;
import com.example.taskmanagerservice.service.TacheService;
import com.example.taskmanagerservice.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/taches")
public class TacheController {

    private final TacheService tacheService;
    private final UtilisateurService utilisateurService;

    @Autowired
    public TacheController(TacheService tacheService, UtilisateurService utilisateurService){
        this.tacheService=tacheService;
        this.utilisateurService=utilisateurService;
    }

    @GetMapping("/taches")
    public ResponseEntity<List<Tache>> getTaches(){
        try {
            List<Tache> taches = tacheService.getTaches();
            return new ResponseEntity<>(taches, HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/tache/{id}")
    public ResponseEntity<Tache> getTache(@PathVariable Long id){
        try {
            Tache tache = tacheService.getTache(id);
            return new ResponseEntity<>(tache, HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/tache")
    public ResponseEntity<Tache> ajouterTache(@RequestBody Tache tache){
        try {
            Tache newTache = tacheService.ajouterTache(tache);
            return new ResponseEntity<>(newTache, HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tacheUtilisateur/{id}")
    public ResponseEntity<List<Tache>> getTacheBytilisateur(@PathVariable Long id){
        Utilisateur utilisateur = utilisateurService.getUtilisateur(id);
        if(utilisateur==null){
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(tacheService.getTacheByUtilisateur(utilisateur), HttpStatus.OK);
        }

    }

    @DeleteMapping("/tache/{id}")
    public ResponseEntity<Tache> supprimerTache(@PathVariable Long id){
        try{
            tacheService.supprimerTache(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }}

    @PutMapping("/tache/{id}")
    public ResponseEntity<Tache> updateTache(@PathVariable Long id, @RequestBody Tache tache){
        try {
            Tache newTache = tacheService.updateTache(id, tache);
            return new ResponseEntity<>(newTache,HttpStatus.OK);
        }catch (RuntimeException e){
            throw new RuntimeException("erreur lors de l'ajout : {}",e);
        }
    }

}
