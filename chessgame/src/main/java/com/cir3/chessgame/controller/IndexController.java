package com.cir3.chessgame.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String welcome(Model model){
        return "index";
    }

    @GetMapping("/login")
    public String login(Authentication authentication) {

        //if authentified we send him on his user page
        if (authentication!= null && authentication.isAuthenticated()) {
            return "redirect:/user/profil";
        }
      
        return "login";
    }
}
