package com.example.taskmanagerservice.DTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class TacheDTO {
    private Long id;

    private String description;
    private Boolean estTermine;
    private LocalDate dateEcheance;
    private Long utilisateur;
}
