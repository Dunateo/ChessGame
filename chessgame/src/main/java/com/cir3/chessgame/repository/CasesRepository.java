package com.cir3.chessgame.repository;

import com.cir3.chessgame.domain.Cases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CasesRepository extends JpaRepository<Cases,Long> {
	 Cases findByXAndYAndPartie_id(int x,int y,Long id);
}
