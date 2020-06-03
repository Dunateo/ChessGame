package com.cir3.chessgame.repository;

import com.cir3.chessgame.domain.Joueur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JoueurRepository extends JpaRepository<Joueur,Long> {
    Joueur findByUsername(String username);
}
