package com.cir3.chessgame.controller.api;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cir3.chessgame.domain.Partie;
import com.cir3.chessgame.domain.Reponse;
import com.cir3.chessgame.repository.CasesRepository;
import com.cir3.chessgame.repository.CouleurRepository;
import com.cir3.chessgame.repository.PartieRepository;
import com.cir3.chessgame.repository.PionRepository;
import com.cir3.chessgame.services.Rules;
import com.cir3.chessgame.services.TourParTour;

@RestController
@RequestMapping("/partie/")
public class PartieApiController {
	
	@Autowired
	private PartieRepository parties;
	@Autowired
	private CasesRepository casesRepo;
	@Autowired
	private CouleurRepository couleursRepo;
	@Autowired
	private PionRepository pionRepo;
	
	int n=0;
	
	
	@GetMapping("{id}/Tour/{xp}/{yp}/{xd}/{yd}")
	public Reponse reponse(Authentication authentication,@PathVariable(required = true)int xp,@PathVariable(required = true)int yp,@PathVariable(required = true)int xd,@PathVariable(required = true)int yd,@PathVariable(required = true)Long id) {

		// initialisation du tableau de cases
		ArrayList<String> path = new ArrayList<String>();
		
		for(int i = 0; i < 8; i++) {
			
			for(int j = 0; j < 8; j++) {
				
				if(casesRepo.findByXAndYAndPartie_id(j, i, id).getPionCase() == null) {
					path.add("/images/vide.png");
				} else {
					path.add(casesRepo.findByXAndYAndPartie_id(j, i, id).getPionCase().getImage());
				}
			}
		}
		
		Reponse r= new Reponse("0",path);
		
		TourParTour t= new TourParTour();
		Rules rule = new Rules(parties,casesRepo,couleursRepo,pionRepo);
		String result = "";
		Optional<Partie> op= parties.findById(id);
		if(op.isPresent()) {
			Partie p=op.get();
	
			//On test si le joueur est dans la partie est que la partie soit en cours
			if(t.JouerDansPartie(parties, authentication, id)  ) {
			//On test que ce soit bien le tour du joueur
			if(t.JoueurTour(parties, authentication, id)) {
				result = rule.checkMove(xp,yp, xd, yd, id, authentication.getName()); // 7-... = inversion de l'axe des ordonees
			if(result.equals("ok"))  {
				//Le coup est joué
				System.out.println("Recu piece ["+xp+":"+yp+"] vers ["+xd+":"+yd+"]");
				r.setMsg("ok");
				r.setTour("adverse");
				
				//UPDATE DU TABLEAU DE JEU
				
				
				//On incremente le tour de 1
				
				p.setTour(p.getTour() +1);
				parties.save(p);
			}else {
				r.setMsg(result);
				r.setTour("joueur");
				}	
			}else {
				r.setMsg("Erreur: Attendez votre tour pour jouer, non de dieu ! ");
				r.setTour("joueur");
			}
			}else {
				r.setMsg("Erreur: vous n'etes pas dans la partie");
			}
		}
		else {
			r.setMsg("Erreur: Partie inexistante");
		}
		
		
		
		return r;
	}
	
	
	@GetMapping("{id}/Init")
	public Reponse init(Authentication authentication,@PathVariable(required = true)Long id) {
		
		// initialisation du tableau de cases
		ArrayList<String> path = new ArrayList<String>();
		
		for(int i = 0; i < 64; i++) {
			path.add(parties.findById(id).get().getTable().get(i).getPionCase().getImage());
		}
		
		Reponse r= new Reponse("0",path);
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
		
		// initialisation du tableau de cases
		ArrayList<String> path = new ArrayList<String>();
		
		for(int i = 0; i < 8; i++) {
			
			for(int j = 0; j < 8; j++) {
				
				if(casesRepo.findByXAndYAndPartie_id(j, i, id).getPionCase() == null) {
					path.add("/images/vide.png");
				} else {
					path.add(casesRepo.findByXAndYAndPartie_id(j, i, id).getPionCase().getImage());
				}
			}
		}
		
		Reponse r= new Reponse("ok",path);
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
