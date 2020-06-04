package com.cir3.chessgame.controller;

import com.cir3.chessgame.domain.Authority;
import com.cir3.chessgame.domain.Joueur;
import com.cir3.chessgame.form.EditForm;
import com.cir3.chessgame.form.JoueurForm;
import com.cir3.chessgame.repository.AuthorityRepository;
import com.cir3.chessgame.repository.JoueurRepository;
import com.cir3.chessgame.services.ImageStock;
import com.cir3.chessgame.services.SaveJoueur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class JoueurController {

    @Autowired
    private JoueurRepository joueur;

    @Autowired
    private AuthorityRepository autho;

    @Value("${file.upload-dir:}")
    private String FOLDER_UPLOAD;

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

    //Le post mapping de edit et register
    @PostMapping("/register")
    public String registerForm(@Valid @ModelAttribute("register") JoueurForm form,@RequestParam("image") MultipartFile image ,BindingResult result, Model model){

        SaveJoueur save = new SaveJoueur(joueur,autho);

        //On regarde si il ya des erreurs dans le formulaire
        if (result.hasErrors()){
            model.addAttribute("register", form);
            return "register";
        }else if (image.isEmpty() && form.getId() == null){
            model.addAttribute("register", form);
            model.addAttribute("file_status", "Votre image est vide !");
            return "register";

        }

        //traite les erreurs de l'enregistrement d'images
         if (!save.createJoueur(image,form,FOLDER_UPLOAD)){
            model.addAttribute("register", form);
            model.addAttribute("file_status", "Problème lors de l'upload !");
            return "register";
        }


        return "redirect:/login";
    }

    //controlleur pour modifier un utilisateur existant
    @GetMapping("/edit")
    public String edit(Authentication authentication, Model model){
        if (authentication == null || !authentication.isAuthenticated()){
            return "redirect:/user/register";
        }

        Joueur edit = joueur.findByUsername(authentication.getName());
        EditForm form = new EditForm();
        form.setId(edit.getId());
        form.setUsername(edit.getUsername());
        model.addAttribute("register", form);
        return "edit";
    }

    @PostMapping("/edit")
    public String editPost(@Valid @ModelAttribute("register") EditForm form,@RequestParam("image") MultipartFile image ,BindingResult result, Model model, Authentication authentication){
        SaveJoueur save = new SaveJoueur(joueur,autho);
        Joueur j1 = joueur.findByUsername(authentication.getName());
        form.setUsername(authentication.getName());

        //On regarde si il ya des erreurs dans le formulaire
        if (result.hasErrors()){
            return "redirect:/user/edit";
        }else if (form.getId() != null && form.getId().equals(j1.getId())){
            if (!image.isEmpty() && save.editImageJoueur(image,form,FOLDER_UPLOAD)){
                return "redirect:/user/profil";
            }else if (form.getPassword() != null && save.editPasswordJoueur(form)){
                return "redirect:/user/profil";
            }else {
                model.addAttribute("register", form);
                model.addAttribute("file_status", "Problème lors de la modification du champ mot de passe ou Image!");
                return "edit";
            }
        }

        return "redirect:/user/profil";
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
