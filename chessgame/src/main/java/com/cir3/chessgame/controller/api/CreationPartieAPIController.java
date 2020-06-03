package com.cir3.chessgame.controller.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cir3.chessgame.domain.Partie;
import com.cir3.chessgame.domain.Reponse;
import com.cir3.chessgame.repository.JoueurRepository;
import com.cir3.chessgame.repository.PartieRepository;

@RestController
@RequestMapping("/Invitation/")
public class CreationPartieAPIController {
	
	@Autowired
	private PartieRepository parties;
	
	@Autowired
    private JoueurRepository joueurs;
	
	@GetMapping("{inviteName}")
	public Reponse init(Authentication authentication,@PathVariable(required = true)String inviteName) {
		
		Reponse r= new Reponse("0");
		//TESTER SI LE JOUEUR EST DANS LA LISTE D'AMIS
		//Creation d'une partie à tour=-1 et etat false avec les deux joueurs
		Partie p = new Partie(joueurs.findByUsername(authentication.getName()),joueurs.findByUsername(inviteName));
		
		parties.save(p);
		return r;
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
		
		
		Optional<Partie> op= parties.findById(id);
		if(op.isPresent()) {
			Partie p=op.get();
		if(!p.isEtat()) {
			p.setEtat(true);
			p.setTour(0);
			//START PARTIE
			p.createPartie(id);
			parties.save(p);
			return "ok";
		}else {
			return "Erreur: Partie deja en cours";
		}
		}else {
			return "Erreur: Partie introuvable";
		}
		
	}
	

}
