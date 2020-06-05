package com.cir3.chessgame.controller.api;

import com.cir3.chessgame.domain.Joueur;
import com.cir3.chessgame.domain.Partie;
import com.cir3.chessgame.model.ProfilReponse;
import com.cir3.chessgame.repository.JoueurRepository;
import com.cir3.chessgame.repository.PartieRepository;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profil")
public class ProfilUpdateApiController {

	@Autowired
	private JoueurRepository joueurs;

	@Autowired
	private PartieRepository parties;

	@GetMapping("/update")
	public ProfilReponse update(Authentication authentication) {
		ProfilReponse response = new ProfilReponse();
		Joueur joueur = joueurs.findByUsername(authentication.getName());
		Iterator<Partie> it = joueur.getPartie().iterator();
		while (it.hasNext()) {
			Partie p = it.next();
			// On cherche toute les parties en mode invitation 
			if (p.getTour() == -1 ) {
				// On check si c'est notre invitation car l'invitation est outjous gerer par le joueur noir
				if(p.getJoueurNoir().getUsername().equals(joueur.getUsername())) {
					response.setIdPartieRequest(p.getId());
				}else {
					
					response.addInvitationPartie(p.getId(),p.getJoueur().get(0).getUsername() +" contre "+p.getJoueur().get(1).getUsername());
				}
			}
		}

		

		return response;

	}
}
