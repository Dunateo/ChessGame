package com.cir3.chessgame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cir3.chessgame.domain.Joueur;
import com.cir3.chessgame.domain.Partie;
import com.cir3.chessgame.repository.JoueurRepository;
import com.cir3.chessgame.repository.PartieRepository;
import com.cir3.chessgame.services.CreationPartie;

@Controller
@RequestMapping("/partie")
public class PartieController {
	
	
	
	@Autowired
	private JoueurRepository joueurs;
	@GetMapping("/{id}")
	public String partie(@PathVariable(required = true)Long id) {
	
		return "partie";
	}
	
	@GetMapping("/forTest")
	public String IforTest() {
		
	CreationPartie g= new CreationPartie();
	
		
		return "redirect:/partie" + g.Creation(joueurs.findByUsername("hihi"),joueurs.findByUsername("huhu"));
	}

}
