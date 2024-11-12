package com.example.taskmanagerservice.service;

import com.example.taskmanagerservice.DTO.TacheDTO;
import com.example.taskmanagerservice.Mapper.TacheMapper;
import com.example.taskmanagerservice.entity.Tache;
import com.example.taskmanagerservice.entity.Utilisateur;
import com.example.taskmanagerservice.repository.TacheRepository;
import com.example.taskmanagerservice.repository.UtilisateurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TacheService {

    private static final Logger logger = LoggerFactory.getLogger(TacheService.class);

    private final TacheRepository tacheRepository;
    private final UtilisateurService utilisateurService;
    private final TacheMapper tacheMapper;

    @Autowired
    public TacheService(TacheRepository tacheRepository, UtilisateurService utilisateurService, TacheMapper tacheMapper) {
        this.tacheRepository = tacheRepository;
        this.utilisateurService = utilisateurService;
        this.tacheMapper = tacheMapper;
    }

    public TacheDTO getTache(long id) {
        logger.info("Récupération de la tâche avec ID : {}", id);
        Tache tache = tacheRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tâche non trouvée avec ID : " + id));
        return tacheMapper.toDTO(tache);
    }

    public List<TacheDTO> getTaches() {
        logger.info("Récupération de toutes les tâches");
        List<Tache> taches = tacheRepository.findAll();
        return taches.stream().map(tacheMapper::toDTO).collect(Collectors.toList());
    }

    public void supprimerTache(Long id) {
        logger.info("Suppression de la tâche : {}", id);
        if (tacheRepository.existsById(id)) {
            tacheRepository.deleteById(id);
        } else {
            throw new RuntimeException("Tâche non trouvée avec ID : " + id);
        }
    }

    public TacheDTO updateTache(long id, TacheDTO tacheDTO) {
        Tache tacheExistant = tacheRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tâche inexistante avec ID : " + id));

        tacheExistant.setDescription(tacheDTO.getDescription());
        tacheExistant.setUtilisateur(utilisateurService.getUtilisateur(tacheDTO.getUtilisateur()));
        tacheExistant.setDateEcheance(tacheDTO.getDateEcheance());
        tacheExistant.setEstTermine(tacheDTO.getEstTermine());

        Tache updatedTache = tacheRepository.save(tacheExistant);
        return tacheMapper.toDTO(updatedTache);
    }

    public TacheDTO ajouterTache(TacheDTO tacheDTO) {
        try {
            logger.info("Ajout d'une nouvelle tâche");
            Utilisateur utilisateur = utilisateurService.getUtilisateur(tacheDTO.getUtilisateur());
            Tache tache = tacheMapper.toEntity(tacheDTO, utilisateur);
            Tache newTache = tacheRepository.save(tache);
            return tacheMapper.toDTO(newTache);
        } catch (RuntimeException e) {
            logger.error("Erreur lors de l'ajout de la tâche : format non conforme", e);
            throw new RuntimeException("Erreur lors de l'ajout de la tâche : format non conforme", e);
        }
    }



    public List<Tache> getTacheByUtilisateur(Utilisateur utilisateur){
        return tacheRepository.findByUtilisateur(utilisateur);
    }
}
