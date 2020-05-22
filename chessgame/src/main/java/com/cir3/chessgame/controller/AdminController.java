package com.cir3.chessgame.controller;

import com.cir3.chessgame.repository.AuthorityRepository;
import com.cir3.chessgame.repository.JoueurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Secured("ROLE_ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private JoueurRepository joueur;

    @Autowired
    private AuthorityRepository autho;

    //mapping pour affichage de la liste des gamemode

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(page=0, size = 10) Pageable pageable){

        model.addAttribute("joueurs", joueur.findAll(pageable));

        return "admin-list";
    }


}
