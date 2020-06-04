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

	public Reponse(String msg, ArrayList<String> pathImage) {
		super();
		this.msg = msg;
		this.Tour="0";
		
		for(String n : pathImage) {
			
			image.add(n);
		}
	}
	
	
	

}
