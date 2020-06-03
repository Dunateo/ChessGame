package com.cir3.chessgame.services;

import org.springframework.security.core.Authentication;

import com.cir3.chessgame.repository.PartieRepository;

public class TourParTour {
	
	public boolean JouerDansPartie(PartieRepository parties,Authentication authentication,Long id) {
		
		for(int i=0;i<parties.findById(id).get().getJoueur().size();i++) {
			if(parties.findById(id).get().getJoueur().get(i).getUsername().equals(authentication.getName())) {
				return true;
			}
		}
		return false;
	}
	public boolean JoueurNoir(PartieRepository parties,Authentication authentication,Long id) {
		
		if(parties.findById(id).get().getJoueurNoir().getUsername().equals(authentication.getName())) {
			return true;
		}else {
			return false;
		}
		
	}
	public boolean JoueurTour(PartieRepository parties,Authentication authentication,Long id) {
		
		
		if(parties.findById(id).get().getJoueurNoir().getUsername().equals(authentication.getName())) {
			//Si c'est le joueur Noir sont tour est impair comme il commence à jouer au tour=1
			if(parties.findById(id).get().getTour() %2 ==0) {
				return false;
			}else {
				return true;
			}
		}else {
			if(parties.findById(id).get().getTour() %2 ==0) {
				return true;
			}else {
				return false;
			}
		}
		
	}

}
