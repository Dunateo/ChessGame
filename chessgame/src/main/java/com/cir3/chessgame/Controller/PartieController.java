package com.cir3.chessgame.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cir3.chessgame.domain.Partie;
import com.cir3.chessgame.repository.PartieRepository;


@Controller
@RequestMapping("/partie")
public class PartieController {
	
	
	@Autowired
	private PartieRepository parties;
	
	@GetMapping("/{id}")
	public String partie(@PathVariable(required = true)Long id,Authentication authentication,Model model) {
	
		int n=0;
		Optional<Partie> op= parties.findById(id);
		if(op.isPresent()) {
			Partie p=op.get();
			for(int i=0;i<p.getJoueur().size();i++) {
				if(!p.getJoueur().get(i).getUsername().equals(authentication.getName())) {
					n=i;
				}
			}
			model.addAttribute("moi",authentication.getName());
			model.addAttribute("adverse",p.getJoueur().get(n).getUsername());
			return "partie";
		}
		
		return "fail";
	}
	
	

}
