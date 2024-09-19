package org.example.game_tournement.Service;

import org.example.game_tournement.Entity.Tournament;
import org.example.game_tournement.dao.TournamentsRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class tournoisService {
    private TournamentsRepository tournamentsRepository;

    public tournoisService(TournamentsRepository tournamentsRepository) {
        this.tournamentsRepository = tournamentsRepository;
    }

    public List<Tournament> getAllTournaments() {
        return tournamentsRepository.findAll();
    }

    public Tournament getTournamentById(int id) {
        return tournamentsRepository.findById(id).orElse(null);
    }

    public void addTournament(Tournament tournament) {
        tournamentsRepository.save(tournament);
    }

    public void deleteTournament(Tournament tournament) {
        tournamentsRepository.delete(tournament);
    }

    public List<Tournament> findByName(String name) {
        return tournamentsRepository.findByNameContainsIgnoreCase(name);
    }
}