package com.cir3.chessgame.controller;

import com.cir3.chessgame.domain.Authority;
import com.cir3.chessgame.domain.Joueur;
import com.cir3.chessgame.form.JoueurForm;
import com.cir3.chessgame.repository.AuthorityRepository;
import com.cir3.chessgame.repository.JoueurRepository;
import com.cir3.chessgame.services.ImageStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/user")
public class JoueurController {

    @Autowired
    private JoueurRepository joueur;

    @Autowired
    private AuthorityRepository autho;

    private static String FOLDER_UPLOAD = "src/resources/static/upload/";

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
    public String registerForm(@Valid @ModelAttribute("register") JoueurForm form,@RequestParam("image") MultipartFile image ,BindingResult result, Model model){

        System.out.println("9a passe" + image.getName());

        //On regarde si il ya des erreurs dans le formulaire
        if (result.hasErrors()){
            System.out.println("9a passe2 " + image.getOriginalFilename());
            model.addAttribute("register", form);
            return "register";
        }else if (image.isEmpty()){
            System.out.println("9a passe3" + image.getName());
            model.addAttribute("register", form);
            model.addAttribute("file_status", "Votre image est vide !");
            return "register";
        }

        ImageStock storage = new ImageStock();

        //traite les erreurs de l'enregistrement d'images
        if (storage.upload(image, form.getUsername(),FOLDER_UPLOAD)){
            //attribution du formulaire à une entities
            Joueur joueurNew = new Joueur();
            joueurNew.setUsername(form.getUsername());
            joueurNew.setPassword(form.getPassword());
            joueurNew.setImage(FOLDER_UPLOAD + form.getUsername());
            joueurNew.setAuthorities(autho.findByAuthorityEquals("ROLE_USER"));
            joueur.save(joueurNew);

        }else {
            System.out.println("9a passe5" + image.getName());

            model.addAttribute("register", form);
            model.addAttribute("file_status", "Problème lors de l'upload !");
            return "register";
        }


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
