package com.cir3.chessgame.domain;

import java.awt.Color;

import javax.persistence.*;

@Entity(name = "cases")
public class Cases {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int x;

    @Column
    private int y;

    @Column
    private boolean etat;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn( updatable = false)
    private Pion pionCase;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn( updatable = false)
    private Partie partie;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public Pion getPionCase() {
        return pionCase;
    }

    public void setPionCase(Pion pionCase) {
        this.pionCase = pionCase;
    }

    public Partie getPartie() {
        return partie;
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }
    
    // Creer une case avec une piece complexe
 	public Cases(int ligne, int colonne, Long mIdCase, Long mIdPiece, String mName) {
 		
 		Pion tPiece = null;
 		
 		Long black = (long) 0;
		Long white = (long) 1;
  		
		Couleur mCoulBlack = null;
  		Couleur mCoulWhite = null;
  		
  		
  		mCoulBlack.createCouleur(black, "Noir");
  		
  		mCoulWhite.createCouleur(white,"Blanc");
 		
 		setId(mIdCase);
 		
 		setX(colonne);
 		
 		setY(ligne);
 		
 		if (ligne < 2) {
 			
 			tPiece.createPion(mIdPiece, mName, mCoulBlack);
 			
 			setEtat(false);
 		}
 		else if (ligne > 5){
 			
 			tPiece.createPion(mIdPiece, mName, mCoulWhite);
 			
 			setEtat(false);
 			
 		}
 		else {
 			
 			setPionCase(tPiece);
 			
 			setEtat(true);
 		}
 	}
 	
 	// Creer une case avec une piece complexe
  	public void createCases(int ligne, int colonne, Long mIdCase, Long mIdPiece, String mName) {
  		
  		Pion tPiece = null;
  		
  		Long black = (long) 0;
		Long white = (long) 1;
  		
		Couleur mCoulBlack = null;
  		Couleur mCoulWhite = null;
  		
  		
  		mCoulBlack.createCouleur(black, "Noir");
  		
  		mCoulWhite.createCouleur(white,"Blanc");
  		
  		setId(mIdCase);
  		
  		setX(colonne);
  		
  		setY(ligne);
  		
  		if (ligne < 2) {
  			
  			tPiece.createPion(mIdPiece, mName, mCoulBlack);
  			
  			setEtat(false);
  		}
  		else if (ligne > 5){
  			
  			tPiece.createPion(mIdPiece, mName, mCoulWhite);
  			
  			setEtat(false);
  			
  		}
  		else {
  			
  			setPionCase(tPiece);
  			
  			setEtat(true);
  		}
  	}
  	
  	
 
}
