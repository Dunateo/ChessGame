package com.cir3.chessgame.controller;

import com.cir3.chessgame.domain.Authority;
import com.cir3.chessgame.domain.Joueur;
import com.cir3.chessgame.repository.AuthorityRepository;
import com.cir3.chessgame.repository.CouleurRepository;
import com.cir3.chessgame.repository.JoueurRepository;
import com.cir3.chessgame.repository.PionRepository;
import com.cir3.chessgame.services.InitBddService;



import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class IndexController {

	
	
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

    

}
