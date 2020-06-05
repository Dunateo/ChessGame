package com.cir3.chessgame.services;

import static org.junit.Assert.assertTrue;

import java.awt.*;
import java.awt.List;
import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cir3.chessgame.domain.Cases;
import com.cir3.chessgame.domain.Couleur;
import com.cir3.chessgame.domain.Partie;
import com.cir3.chessgame.repository.AuthorityRepository;
import com.cir3.chessgame.repository.CasesRepository;
import com.cir3.chessgame.repository.CouleurRepository;
import com.cir3.chessgame.repository.PartieRepository;
import com.cir3.chessgame.repository.PionRepository;

@SpringBootTest
@DataJpaTest
public class RulesTest {
	
	@Autowired
	PartieRepository games;
	@Autowired
	CasesRepository Case;
	@Autowired
	CouleurRepository Couleurs;
	@Autowired
	PionRepository Pions;
	@Autowired
    private AuthorityRepository autho;
    
    @Value("${pion.upload-dir:}")
    private String FOLDER_PION;
	
	@Test
	public void TestOtherCoul() {
		
		InitBddService initBDD = new InitBddService(Pions,Couleurs);
        initBDD.setFOLDER_PION(FOLDER_PION);
    
	    initBDD.createCouleurs("Blanc");
	    initBDD.createCouleurs("Noir");
	    
		Rules r = new Rules(games,Case,Couleurs,Pions);
		
		Couleur res1 = new Couleur((long) 1, "Blanc");
		Couleur res2 = new Couleur((long) 2, "Noir");
		
		assertTrue(r.otherCoul(new Couleur("Noir")) == res1);
		
		assertTrue(r.otherCoul(new Couleur("Blanc")) == res2);
	}
	
	@Test
	public void TestMovePion() {
		
		Rules r = new Rules(games,Case,Couleurs,Pions);
		
		Long id = (long) 1;
		
		Partie p = new Partie();
		
		p.createPartie(id);
		
		r.givePiece(id);
		
		r.giveCouleur(id);
		
		for(int k = 0; k < 8; k++) {
			
			Cases myCase = r.returnCaseCoords(k, 1, id);
			
			for(int i = 0; i < 8; i++) {
				
				for(int j = 0; j < 8; j++) {
					
					if (k == j && (i == 2 || i == 3))
						assertTrue(r.checkPion(myCase, j, i, id) == true);
					else
						assertTrue(r.checkPion(myCase, j, i, id) == false);
				}
			}
		}
	}
}
