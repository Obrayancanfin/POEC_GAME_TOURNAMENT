package org.example.game_tournement.dao;

import org.example.game_tournement.Entity.Matche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchsRepository extends JpaRepository<Matche, Integer> {

}
