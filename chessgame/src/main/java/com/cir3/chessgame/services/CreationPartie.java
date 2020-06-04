package com.cir3.chessgame.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cir3.chessgame.domain.Cases;
import com.cir3.chessgame.domain.Joueur;
import com.cir3.chessgame.domain.Partie;
import com.cir3.chessgame.repository.PartieRepository;

public class CreationPartie {

	@Autowired
	private PartieRepository parties;
	
	
	public Long Creation(Joueur j1,Joueur j2) {
		Partie p= new Partie();
		p.setEtat(true);
		p.AddJoueur(j1);
		p.AddJoueur(j2);
		p.setJoueurNoir(j1);
		p.setTour(0);
		
		System.out.println("ok");
		List<Cases> table = new ArrayList<>();
		p.setTable(table);
			
		parties.save(p);
		System.out.println("ok2");
		
		
		
		
		return (long)3;
	}


}
