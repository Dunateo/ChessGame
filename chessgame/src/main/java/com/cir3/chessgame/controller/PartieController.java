package com.cir3.chessgame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cir3.chessgame.domain.Cases;
import com.cir3.chessgame.domain.Couleur;
import com.cir3.chessgame.domain.Partie;
import com.cir3.chessgame.domain.Pion;
import com.cir3.chessgame.repository.CasesRepository;
import com.cir3.chessgame.repository.CouleurRepository;
import com.cir3.chessgame.repository.PartieRepository;

@Controller
@RequestMapping("/partie")
public class PartieController {
	
	@GetMapping("")
	public String partie() {
		
	  	return "partie";
	}
}
