package com.cir3.chessgame.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cir3.chessgame.domain.Reponse;

@RestController
@RequestMapping("/partie/")
public class PartieApiController {
	int n=0;
	
	
	@GetMapping("Tour/{xp}/{yp}/{xd}/{yd}")
	//public Reponse reponse(@PathVariable(required = true)Long xp,@PathVariable(required = true)Long yp,@PathVariable(required = true)Long xd,@PathVariable(required = true)Long yd) {
	public Reponse reponse(@PathVariable(required = true)Long xp,@PathVariable(required = true)Long yp,@PathVariable(required = true)Long xd,@PathVariable(required = true)Long yd) {
		Reponse r= new Reponse("0");
		System.out.println("Recu piece ["+xp+":"+yp+"] vers ["+xd+":"+yd+"]");
		if(xp==0 && yp==1 && xd==0 && yd==3)  {
			r.setMsg("ok");
			r.setTour("adverse");
		}else {
			r.setMsg("Erreur: coup Impossible");
			r.setTour("joueur");
		}
		
		
		return r;
	}
	@GetMapping("Init")
	//public Reponse reponse(@PathVariable(required = true)Long xp,@PathVariable(required = true)Long yp,@PathVariable(required = true)Long xd,@PathVariable(required = true)Long yd) {
	public Reponse init() {
		Reponse r= new Reponse("ok");
		r.setTour("joueur");
		
		
		return r;
	}
	@GetMapping("UPDATE")
	public Reponse update() {
		Reponse r= new Reponse("ok");
		System.out.println("Demande "+n+"fois");
		if(n==0) {
			r.setTour("adverse");
			n++;
		}else if(n<=1) {
			r.setTour("joueur");
			r.setImage(0,"images/PionNoir.png");
			n=0;
		}
		
		
		
		
		return r;
	}
	
}
