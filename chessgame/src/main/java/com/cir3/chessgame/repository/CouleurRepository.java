package com.cir3.chessgame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cir3.chessgame.domain.Couleur;

@Repository
public interface CouleurRepository extends JpaRepository<Couleur,Long> {
}
