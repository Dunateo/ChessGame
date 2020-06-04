package com.cir3.chessgame.repository;

import com.cir3.chessgame.domain.Joueur;
import com.cir3.chessgame.domain.Partie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartieRepository extends JpaRepository<Partie,Long> {
    List<Partie> findAllByJoueurAndTour(Joueur player,int tour);
}
