package com.cir3.chessgame.controller;

import com.cir3.chessgame.domain.Authority;
import com.cir3.chessgame.domain.Joueur;
import com.cir3.chessgame.repository.AuthorityRepository;
import com.cir3.chessgame.repository.JoueurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class JoueurController {
    @Autowired
    private JoueurRepository joueur;

    @GetMapping("/register")
    public String register(Authentication authentication){

        //if authentified we send him on his user page
        if (authentication.isAuthenticated()){
            return "redirect:/user/profil";
        }

        return "register";
    }


    @GetMapping("/profil")
    public String profil(Authentication authentication, Model model){

        //if not authentified we send him on login page
        if (!authentication.isAuthenticated()){
            return "redirect:/login";
        }
        //get the player
        Joueur profil = joueur.findByUsername(authentication.getName());

        model.addAttribute("joueur", profil);

        return "profil";
    }




}
