package com.cir3.chessgame.controller;

import com.cir3.chessgame.domain.Authority;
import com.cir3.chessgame.domain.Joueur;
import com.cir3.chessgame.repository.AuthorityRepository;
import com.cir3.chessgame.repository.JoueurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class IndexController {

    @Autowired
    private AuthorityRepository autho;

    @Autowired
    private JoueurRepository joueur;

    //default controller
    @GetMapping("/")
    public String welcome(Model model){
        return "index";
    }

    //controller for login
    @GetMapping("/login")
    public String login(Authentication authentication) {

        //if authentified we send him on his user page
        if (authentication!= null && authentication.isAuthenticated()) {
            return "redirect:/user/profil";
        }

        return "login";
    }

    @GetMapping("/error")
    public String error(){
        return "error";
    }
}
