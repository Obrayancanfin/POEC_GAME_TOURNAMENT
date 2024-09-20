package org.example.game_tournement.dao;

import org.example.game_tournement.Entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TournamentsRepository extends JpaRepository <Tournament, Integer>{
        //List<Tournament> getAllTournaments();

        List<Tournament> findByNameContainsIgnoreCase(String name);
    }
