package com.cir3.chessgame.repository;

import com.cir3.chessgame.domain.Joueur;
import com.cir3.chessgame.domain.Partie;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JoueurRepository extends JpaRepository<Joueur,Long> {
    Joueur findByUsername(String username);
    Set<Joueur> findAllByUsernameIsLike(String username);
    
}
