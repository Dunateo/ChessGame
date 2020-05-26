package com.cir3.chessgame.controller;

import com.cir3.chessgame.repository.AuthorityRepository;
import com.cir3.chessgame.repository.JoueurRepository;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Secured("ROLE_ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private JoueurRepository joueur;

    @Autowired
    private AuthorityRepository autho;

    private static Logger logger;

    //mapping pour affichage de la liste des gamemode

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(page=0, size = 10) Pageable pageable,  Authentication authentication){

        model.addAttribute("joueurs", joueur.findAll(pageable));
        logger.warn("La liste des utilisateurs vient d'être affichée: "+ authentication.getName());

        return "admin-list";
    }

    //supprime un utilisateur
    @GetMapping("/delete/{id}")
    public String remove(@PathVariable Long id, Authentication authentication){
        joueur.deleteById(id);
        logger.warn("Un utilisateur vient d'être supprimé par: "+ authentication.getName());

        return "redirect:/admin/list";
    }


}
