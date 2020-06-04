package com.cir3.chessgame.controller;

import com.cir3.chessgame.domain.Joueur;
import com.cir3.chessgame.repository.JoueurRepository;
import com.cir3.chessgame.services.DbUserDetailsService;
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


    @GetMapping("/invite/{invite}")
    public String inviteFriendToFriendList(Authentication authentification, @PathVariable(required = true)String invite ){

        //Test si present dans invite list
        //Test si present dans Friend list
        //Test si player existe
        //Test invite soi meme
        //Crétion dns

        FriendService f = new FriendService(joueur);
        return f.addToInviteList(authentification.getName(),invite);
    }


    /*@GetMapping("/friends/add")
    public String addFriendByUsername(Joueur joueur){
        joueur.addAttribute("add_friends", DbUserDetailsService.addFriendByUsername());
        return "friends/list";
    }

    @GetMapping("/friends/delete")
    public String deleteFriendByUsername(Joueur joueur){
        joueur.addAttribute("delete_friends", DbUserDetailsService.deleteFriendByUsername());
        return "friends/list";
    }

    @GetMapping("/friends/invite")
    public String inviteFriendByUsername(Joueur joueur){
        joueur.addAttribute("invite_friends", DbUserDetailsService.inviteFriendByUsername());
        return "friends/list";
    }

    @GetMapping("/friends")
    public String findAll(Joueur joueur){
        joueur.addAttribute("friends", DbUserDetailsService.findAll());
        return "friends/list";
    }*/

}
