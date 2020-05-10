package com.cir3.chessgame.repository;

import com.cir3.chessgame.domain.Pion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PionRepository extends JpaRepository<Pion,Long> {
}
