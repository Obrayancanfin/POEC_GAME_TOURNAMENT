package org.example.game_tournement.Service;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import org.example.game_tournement.Entity.Matche;
import org.example.game_tournement.dao.MatchsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchsService {
    private MatchsRepository matchsRepository;

    public MatchsService(MatchsRepository matchsRepository) {
        this.matchsRepository = matchsRepository;
    }

    public List<Matche> getAllMatches() {
        return  matchsRepository.findAll();
    }

    public Matche getMatchById(int id) {
        return matchsRepository.findById(id).orElse(null);
    }

    public Matche addMatch(Matche match) {
        return matchsRepository.save(match);
    }

    public void deleteMatch(Matche match) {
        matchsRepository.delete(match);
    }

}
