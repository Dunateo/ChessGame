package com.cir3.chessgame.repository;

import com.cir3.chessgame.domain.Couleur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouleurRepository extends JpaRepository<Couleur,Long> {
}
