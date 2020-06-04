package com.cir3.chessgame.controller.api;

import com.cir3.chessgame.domain.Joueur;
import com.cir3.chessgame.repository.JoueurRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * REST CONTROLLER PUBLIC AFIN D'ACCEDER AUX DONNÉES d'utilisateur
 */
@RestController
@RequestMapping("/api/joueur")
@Secured({"ROLE_USER", "ROLE_ADMIN"})
public class SearchPlayerApi {

    @Autowired
    private JoueurRepository joueurs;

    @GetMapping("/{text}")
    @JsonView(Joueur.JoueurView.BasicData.class)
    public Set<Joueur> get(@PathVariable(required = true) String text){

        return joueurs.findAllByUsernameIsLike('%'+text+'%');
    }
}
