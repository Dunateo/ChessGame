package com.cir3.chessgame.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cir3.chessgame.domain.Joueur;
import com.cir3.chessgame.domain.Partie;
import com.cir3.chessgame.domain.Reponse;
import com.cir3.chessgame.repository.PartieRepository;
import com.cir3.chessgame.services.TourParTour;

@RestController
@RequestMapping("/partie/")
public class PartieApiController {
	
	@Autowired
	private PartieRepository parties;
	
	int n=0;
	
	
	@GetMapping("{id}/Tour/{xp}/{yp}/{xd}/{yd}")
	public Reponse reponse(Authentication authentication,@PathVariable(required = true)Long xp,@PathVariable(required = true)Long yp,@PathVariable(required = true)Long xd,@PathVariable(required = true)Long yd,@PathVariable(required = true)Long id) {

		Reponse r= new Reponse("0");
		TourParTour t= new TourParTour();
		//On test si le joueur est dans la partie est que la partie soit en cours
		if(t.JouerDansPartie(parties, authentication, id)  ) {
		//On test que ce soit bien le tour du joueur
		if(t.JoueurTour(parties, authentication, id)) {
		if(xp==0 && yp==1 && xd==0 && yd==3)  {
			//Le coup est joué
			System.out.println("Recu piece ["+xp+":"+yp+"] vers ["+xd+":"+yd+"]");
			r.setMsg("ok");
			r.setTour("adverse");
			
			//UPDATE DU TABLEAU DE JEU
			
			//On incremente le tour de 1
			Partie p =  parties.findById(id).get();
			p.setTour(p.getTour() +1);
			parties.save(p);
		}else {
			r.setMsg("Erreur: coup Impossible");
			r.setTour("joueur");
			}	
		}else {
			r.setMsg("Erreur: Attendez votre tour pour jouer, non de dieu ! ");
			r.setTour("joueur");
		}
		}else {
			r.setMsg("Erreur: vous n'etes pas dans la partie");
		}
		
		
		return r;
	}
	
	
	@GetMapping("{id}/Init")
	public Reponse init(Authentication authentication,@PathVariable(required = true)Long id) {
		
		Reponse r= new Reponse("0");
		TourParTour t= new TourParTour();
		//On test si le joueur est dans la partie
		if(parties.findById(id).get().getTour() <0) {
		if(t.JouerDansPartie(parties, authentication, id)) {
			r.setMsg("ok");
			
			// INITIALISATION DU PLATEAU
			//On etablie qui est le joueurs noir et qui est le blanc, le joueur noir commence
			if(t.JoueurNoir(parties, authentication, id)) {
				r.setTour("adverse");
			}else {
				r.setTour("joueur");
			}
		}else {
			r.setMsg("ModeSpectateur");
		}
		}else {
			r.setMsg("La Partie est en cours");
		}
		
		
		
		
		return r;
	}
	
	
	@GetMapping("{id}/UPDATE")
	public Reponse update(Authentication authentication,@PathVariable(required = true)Long id) {
		Reponse r= new Reponse("ok");
		TourParTour t= new TourParTour();
		//UPDATE DU PLATEAU DE JEU
		
		//On teste que le joueur est dans la partie
		if(t.JouerDansPartie(parties, authentication, id)) {
		//On teste si c'est son tour
		if(t.JoueurTour(parties, authentication, id)) {
			r.setTour("joueur");
		}else {
			r.setTour("adverse");
		}
		}else {
			r.setMsg("ModeSpectateur");
		}
		
		return r;
	}
	
}
