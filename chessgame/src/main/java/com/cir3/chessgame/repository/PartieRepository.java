package com.cir3.chessgame.repository;

import com.cir3.chessgame.domain.Partie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartieRepository extends JpaRepository<Partie,Long> {
}
