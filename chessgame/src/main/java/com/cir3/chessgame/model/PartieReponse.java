package com.cir3.chessgame.model;

public class PartieReponse {
	Long id;
	String versus;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getVersus() {
		return versus;
	}
	public void setVersus(String versus) {
		this.versus = versus;
	}
	public PartieReponse(Long id, String versus) {
		
		this.id = id;
		this.versus = versus;
	}
	

}
