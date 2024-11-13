package com.example.taskmanagerservice.controller;


import com.example.taskmanagerservice.DTO.UtilisateurDTO;
import com.example.taskmanagerservice.Mapper.UtilisateurMapper;
import com.example.taskmanagerservice.entity.Utilisateur;
import com.example.taskmanagerservice.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateur")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;
    private final UtilisateurMapper utilisateurMapper;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService, UtilisateurMapper utilisateurMapper){
        this.utilisateurService=utilisateurService;
        this.utilisateurMapper = utilisateurMapper;
    }


    @GetMapping("/utilisateurs")
    public ResponseEntity<List<UtilisateurDTO>> getUtilisateurs(){
        try{
        List<UtilisateurDTO> utilisateurs = utilisateurService.getAllUtilisateurs();
        return new ResponseEntity<>(utilisateurs, HttpStatus.OK);
    }catch (RuntimeException e){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/utilisateur/{id}")
    public ResponseEntity<UtilisateurDTO> getUtilisateur(@PathVariable Long id){
        try{
            UtilisateurDTO utilisateur = utilisateurService.getUtilisateur(id);
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

    @PutMapping("/utilisateur/{id}")
    public ResponseEntity<UtilisateurDTO> updateUtilisateur(@PathVariable Long id, @RequestBody UtilisateurDTO utilisateurDTO){
        try {
            UtilisateurDTO newUtilisateur = utilisateurService.updateUtilisateur(id, utilisateurDTO);
            return new ResponseEntity<>(newUtilisateur, HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
