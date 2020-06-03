package com.cir3.chessgame.controller.api;

import com.cir3.chessgame.domain.Joueur;
import com.cir3.chessgame.services.DbUserDetailsService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/friends")
public class FriendController {




    @GetMapping("/{invite}")
    public String inviteFriend(Authentication authentification, @PathVariable(required = true)String invite ){

        return "demande d'amis de  "+authentification.getName()+"à "+invite;
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
