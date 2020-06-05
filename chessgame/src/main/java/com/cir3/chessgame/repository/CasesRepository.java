package com.cir3.chessgame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cir3.chessgame.domain.Cases;


@Repository
public interface CasesRepository extends JpaRepository<Cases,Long> {
	
	public Cases findByXAndYAndPartie_id(int x, int y, Long id) ;
}
