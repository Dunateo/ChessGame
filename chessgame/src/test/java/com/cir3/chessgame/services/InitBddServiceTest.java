package com.cir3.chessgame.services;

import com.cir3.chessgame.ChessgameApplication;
import com.cir3.chessgame.repository.CouleurRepository;
import com.cir3.chessgame.repository.PionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class InitBddServiceTest {

    @Autowired
    private PionRepository pions;

    @Autowired
    private CouleurRepository couleurs;

    @Value("${pion.upload-dir:}")
    private String FOLDER_PION;


   @Test
    void testCreationCouleur(){
       //initialisation
        InitBddService initBDD = new InitBddService(this.pions,this.couleurs);
        assertEquals(Boolean.TRUE, initBDD.createCouleurs("Blanc"), "Création d'une couleur réussie");

    }

    @Test
     void testErreurCreationCouleur(){
        //initialisation
        InitBddService initBDD = new InitBddService(this.pions,this.couleurs);
        assertEquals(Boolean.FALSE, initBDD.createCouleurs(""), "Création d'une couleur erreur");

    }

}
