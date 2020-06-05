package com.cir3.chessgame.controller.api;

import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cir3.chessgame.domain.Joueur;
import com.cir3.chessgame.domain.Partie;
import com.cir3.chessgame.domain.Reponse;
import com.cir3.chessgame.repository.CasesRepository;
import com.cir3.chessgame.repository.CouleurRepository;
import com.cir3.chessgame.repository.JoueurRepository;
import com.cir3.chessgame.repository.PartieRepository;
import com.cir3.chessgame.repository.PionRepository;
import com.cir3.chessgame.services.Rules;

@RestController
@RequestMapping("/Invitation/")
public class CreationPartieAPIController {

	@Autowired
	private PartieRepository parties;

	@Autowired
    private JoueurRepository joueurs;

	@Autowired
	private CasesRepository casesRepo;
	@Autowired
	private CouleurRepository couleursRepo;
	@Autowired
	private PionRepository pionsRepo;

	@GetMapping("{inviteName}")
	public String init(Authentication authentication,@PathVariable(required = true)String inviteName) {


		//TESTER SI LE JOUEUR EST DANS LA LISTE D'AMIS
		//On test si le joueur n'a pas deja fais une invitation pas accepté
		Joueur joueur = joueurs.findByUsername(authentication.getName());
		Iterator<Partie> it = joueur.getPartie().iterator();
		while (it.hasNext()) {
			Partie p = it.next();
			// On cherche toute les parties en mode invitation 
			if (p.getTour() == -1 ) {
				// On check si c'est notre invitation car l'invitation est outjous gerer par le joueur noir
				if(p.getJoueurNoir().getUsername().equals(joueur.getUsername())) {
					return "Erreur: Vous avez deja fais une invitation à un autre joueur";
				}
			}
		}
		//Creation d'une partie à tour=-1 et etat false avec les deux joueurs
		
		Partie p = new Partie(joueurs.findByUsername(authentication.getName()),joueurs.findByUsername(inviteName));

		parties.saveAndFlush(p);

		return p.getId().toString();
	}

	@GetMapping("AnyInvit")
	public String AnyInvit(Authentication authentication) {

		//Recherche dans sa liste d'amis si une partie est en mode invitation.
		return joueurs.findByUsername(authentication.getName()).getInvitationList();
	}
	@GetMapping("{id}/AnyAccept")
	public String AnyAccept(Authentication authentication,@PathVariable(required = true)Long id) {

		//Test sur une partie qui etait en mode invitation si elle à été accepter
		Optional<Partie> op= parties.findById(id);
		if(op.isPresent()) {
			Partie p=op.get();
			if(p.isEtat()) {
				return "ok";
			}else {
				return "pas accepte";
			}
			}else {
				return "Erreur: Partie introuvable";
			}



	}

	@GetMapping("{id}/Accept")
	public String AcceptInvitation(Authentication authentication,@PathVariable(required = true)Long id) {
		
		
		
		Rules rule = new Rules(parties,casesRepo,couleursRepo,pionsRepo);
		
		Optional<Partie> op= parties.findById(id);
		
		if(op.isPresent()) {
			Partie p=op.get();
		if(!p.isEtat()) {
			p.setEtat(true);
			p.setTour(0);
			//START PARTIE
			p.createPartie(id);
			rule.givePiece(id);
			rule.giveCouleur(id);
			
			parties.save(p);
			
			//return p.getTable().get(5).getId().toString();
		}else {
			return "Erreur: Partie deja en cours";
		}
		}else {
			return "Erreur: Partie introuvable";
		}
		return "ok";
	}


}
