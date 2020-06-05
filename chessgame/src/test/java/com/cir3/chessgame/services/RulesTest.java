package com.cir3.chessgame.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.cir3.chessgame.repository.AuthorityRepository;
import com.cir3.chessgame.repository.CasesRepository;
import com.cir3.chessgame.repository.CouleurRepository;
import com.cir3.chessgame.repository.PartieRepository;
import com.cir3.chessgame.repository.PionRepository;

@SpringBootTest
public class RulesTest {
	
	@Autowired
    private PartieRepository parties;
	
	@Autowired
    private CasesRepository cases;
	
	@Autowired
    private AuthorityRepository autho;
	
	@Autowired
    private PionRepository pions;

    @Autowired
    private CouleurRepository couleurs;

    @Value("${pion.upload-dir:}")
    private String FOLDER_PION;
	
	@Test
	public void otherCouleurTest() {
		
		Rules r = new Rules(parties,cases,couleurs,pions);
		
		
	}
}
