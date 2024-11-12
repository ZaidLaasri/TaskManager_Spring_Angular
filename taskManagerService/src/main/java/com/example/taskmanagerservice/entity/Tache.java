package com.example.taskmanagerservice.entity;


import jakarta.persistence.*;

import java.time.LocalDate;
import com.example.taskmanagerservice.entity.Utilisateur;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Boolean estTermine;
    private LocalDate dateEcheance;



   @ManyToOne
   @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

   public Tache(){};
}
