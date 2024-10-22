package com.example.taskmanagerservice.service;

import com.example.taskmanagerservice.entity.Tache;
import com.example.taskmanagerservice.entity.Utilisateur;
import com.example.taskmanagerservice.repository.TacheRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TacheService {

    private static final Logger logger = LoggerFactory.getLogger(TacheService.class);

    private final TacheRepository tacheRepository;

    @Autowired
    public TacheService(TacheRepository tacheRepository){
        this.tacheRepository=tacheRepository;
    }

    public Tache getTache(long id){
        logger.info("Récupération de la tâche avec ID : {}", id);
        return tacheRepository.findById(id).orElseThrow(()-> new RuntimeException("Tâche non trouvé"));
    }

    public List<Tache> getTaches(){
        logger.info("Récupération de toutes les tâches");
        return tacheRepository.findAll();
    }

    public void supprimerTache(Long id){
        logger.info("suppression de la tache : {} ", id);
        if(tacheRepository.existsById(id)) {
            tacheRepository.deleteById(id);
        }else{
            throw new RuntimeException("Tache non trouvée");
        }
    }

    public Tache updateTache(long id, Tache tacheDetail){
        Tache tacheExistant = tacheRepository.findById(id).orElseThrow(()-> new RuntimeException("tache inexistante"));

        tacheExistant.setDescription(tacheDetail.getDescription());
        tacheExistant.setUtilisateur(tacheDetail.getUtilisateur());
        tacheExistant.setDateEcheance(tacheDetail.getDateEcheance());
        tacheExistant.setEstTermine(tacheDetail.getEstTermine());

        return tacheRepository.save(tacheExistant);
    }

    public Tache ajouterTache(Tache tache){
        try {
            logger.info("Ajout d'une nouvelle tache");
            return tacheRepository.save(tache);
        }catch (RuntimeException e){
            logger.error("Erreur lors de l'ajout de la tâche : format non conforme", e);
            throw new RuntimeException("erreur lors de l'ajout format non conforme", e);
        }

    }

    public List<Tache> getTacheByUtilisateur(Utilisateur utilisateur){
        return tacheRepository.findByUtilisateur(utilisateur);
    }
}
