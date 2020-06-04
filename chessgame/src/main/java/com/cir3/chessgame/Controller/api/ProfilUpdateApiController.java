package com.cir3.chessgame.controller.api;

import com.cir3.chessgame.domain.Joueur;
import com.cir3.chessgame.model.ProfilReponse;
import com.cir3.chessgame.repository.JoueurRepository;
import com.cir3.chessgame.repository.PartieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profil")
@Secured({"ROLE_USER", "ROLE_ADMIN"})
public class ProfilUpdateApiController {

    @Autowired
    private JoueurRepository joueurs;

    @Autowired
    private PartieRepository parties;

    @GetMapping("/update")
    public ProfilReponse update(Authentication authentication){
        ProfilReponse response = new ProfilReponse();
        Joueur joueur = joueurs.findByUsername(authentication.getName());

        //response.setInvitationPartie();
        //response.setIdPartieRequest(parties.findByJoueurNoir_UsernameAndTourEquals(joueur.getUsername(), -1).getId());

        return response;

    }
}
