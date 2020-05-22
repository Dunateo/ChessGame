package com.cir3.chessgame.controller;

import com.cir3.chessgame.domain.Authority;
import com.cir3.chessgame.domain.Joueur;
import com.cir3.chessgame.form.JoueurForm;
import com.cir3.chessgame.repository.AuthorityRepository;
import com.cir3.chessgame.repository.JoueurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class JoueurController {

    @Autowired
    private JoueurRepository joueur;

    @Autowired
    private AuthorityRepository autho;


    //partie pour créer un utilisateur
    @GetMapping("/register")
    public String register(Authentication authentication, Model model){

        //if authentified we send him on his user page
        if (authentication != null && authentication.isAuthenticated()){
            return "redirect:/user/profil";
        }
        JoueurForm form = new JoueurForm();
        model.addAttribute("register", form);

        return "register";
    }

    @PostMapping("/register")
    public String registerForm(@Valid @ModelAttribute("register") JoueurForm form, BindingResult result, Model model){

        //On regarde si il ya des erreurs dans le formulaire
        if (result.hasErrors()){
            model.addAttribute("register", form);
            return "register";
        }
        //attribution du formulaire à une entities
        Joueur joueurNew = new Joueur();
        joueurNew.setUsername(form.getUsername());
        joueurNew.setPassword(form.getPassword());
        joueurNew.setImage(form.getImage());
        joueurNew.setAuthorities(autho.findByAuthorityEquals("ROLE_USER"));

        joueur.save(joueurNew);

        return "redirect:/login";
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
