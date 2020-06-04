package com.cir3.chessgame.controller;

import com.cir3.chessgame.domain.Friends;
import com.cir3.chessgame.domain.Joueur;
import com.cir3.chessgame.repository.FriendsRepository;
import com.cir3.chessgame.repository.JoueurRepository;
import com.cir3.chessgame.services.FriendService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendController {

    @Autowired
    private JoueurRepository joueur;

    @Autowired
    private FriendsRepository friends;


    @GetMapping("/invite/{invite}")
    public Boolean inviteFriendToFriendList(Authentication authentification, @PathVariable(required = true)String invite ){

        //Test si present dans invite list
        //Test si present dans Friend list
        //Test si player existe
        //Test invite soi meme
        //Crétion dns

        FriendService f = new FriendService(joueur, friends);
        return f.addToInviteList(authentification.getName(),invite);
    }

    @GetMapping("/affich/invit")
    @Secured("ROLE_ADMIN")
    @JsonView(Joueur.JoueurView.BasicData.class)
    public List<Joueur> affich(Authentication authentication){

        return  joueur.findByUsername(authentication.getName()).getFriends().getInviteList();
    }

    @GetMapping("/affich/amis")
    @Secured("ROLE_ADMIN")
    @JsonView(Joueur.JoueurView.BasicData.class)
    public List<Joueur> affichAmis(Authentication authentication){

        return joueur.findByUsername(authentication.getName()).getFriends().getFriendsList();

    }

    @GetMapping("/accepte/{invite}")
    public Boolean AccepteFriendToFriendList(Authentication authentification, @PathVariable(required = true)String invite ){

        FriendService fl = new FriendService(joueur, friends);

        return fl.addToFriendList(authentification.getName(),invite);
    }
    

}
