package com.cir3.chessgame.domain;

import java.util.ArrayList;

public class Reponse {
	private ArrayList<String> image = new ArrayList<>();
	
	private String msg;
	private String Tour;

	public String getTour() {
		return Tour;
	}

	public void setTour(String tour) {
		Tour = tour;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ArrayList<String> getImage() {
		return image;
	}

	public void setImage(int n,String nom) {
		this.image.set(n, nom);
	}

	public Reponse(String msg) {
		super();
		this.msg = msg;
		this.Tour="0";
		
		this.image.add("images/TourNoir.png");
		this.image.add("images/CavalierNoir.png");
		this.image.add("images/FouNoir.png");
		this.image.add("images/ReinneNoir.png");
		this.image.add("images/RoiNoir.png");
		this.image.add("images/FouNoir.png");
		this.image.add("images/CavalierNoir.png");
		this.image.add("images/TourNoir.png");
		
		this.image.add("images/PionNoir.png");
		this.image.add("images/PionNoir.png");
		this.image.add("images/PionNoir.png");
		this.image.add("images/PionNoir.png");
		this.image.add("images/PionNoir.png");
		this.image.add("images/PionNoir.png");
		this.image.add("images/PionNoir.png");
		this.image.add("images/PionNoir.png");
		
		for (int i=0; i<8*4;i++) {
			this.image.add("images/Vide.png");
		}
		
		
		this.image.add("images/PionBlanc.png");
		this.image.add("images/PionBlanc.png");
		this.image.add("images/PionBlanc.png");
		this.image.add("images/PionBlanc.png");
		this.image.add("images/PionBlanc.png");
		this.image.add("images/PionBlanc.png");
		this.image.add("images/PionBlanc.png");
		this.image.add("images/PionBlanc.png");
		
		this.image.add("images/TourBlanc.png");
		this.image.add("images/CavalierBlanc.png");
		this.image.add("images/FouBlanc.png");
		this.image.add("images/ReinneBlanc.png");
		this.image.add("images/RoiBlanc.png");
		this.image.add("images/FouBlanc.png");
		this.image.add("images/CavalierBlanc.png");
		this.image.add("images/TourBlanc.png");
		
	}
	
	
	

}
