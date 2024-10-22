package com.example.taskmanagerservice.repository;

import com.example.taskmanagerservice.entity.Tache;
import com.example.taskmanagerservice.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TacheRepository extends JpaRepository<Tache, Long> {
    List<Tache> findByUtilisateur(Utilisateur utilisateur);
}
