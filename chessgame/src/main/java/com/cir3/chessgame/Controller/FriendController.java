package com.cir3.chessgame.controller;

import com.cir3.chessgame.domain.Friends;
import com.cir3.chessgame.domain.Joueur;
import com.cir3.chessgame.repository.FriendsRepository;
import com.cir3.chessgame.repository.JoueurRepository;
import com.cir3.chessgame.services.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/friends")
public class FriendController {

    @Autowired
    private JoueurRepository joueur;

    @Autowired
    private FriendsRepository friends;


    @GetMapping("/invite/{invite}")
    public String inviteFriendToFriendList(Authentication authentification, @PathVariable(required = true)String invite ){

        //Test si present dans invite list
        //Test si present dans Friend list
        //Test si player existe
        //Test invite soi meme
        //Crétion dns

        FriendService f = new FriendService(joueur, friends);
        f.addToInviteList(authentification.getName(),invite);

        return "profil";
    }

    @GetMapping("/affich")
    public void affich(Authentication authentication){
        for (Joueur f: joueur.findByUsername(authentication.getName()).getFriends().getInviteList()){
            System.out.println("AFFICHAGE INVITE: "+f.getUsername());
        }

    }

    @GetMapping("/accepte/{invite}")
    public String AccepteFriendToFriendList(Authentication authentification, @PathVariable(required = true)String invite ){

        FriendService fl = new FriendService(joueur, friends);
        fl.addToFriendList(authentification.getName(),invite);
        return "profil";
    }
    

}
