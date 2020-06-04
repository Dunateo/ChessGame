package com.cir3.chessgame.domain;

import javax.persistence.*;

import com.cir3.chessgame.repository.PartieRepository;

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

    @ManyToOne(cascade = {CascadeType.ALL})
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
    
    public Cases () {
    	
    }
    
    
	// Creer une case avec une piece complexe
 	public Cases(int ligne, int colonne, Long mIdCase, Long mIdPiece, String mName, Partie game) {
 		
 		Long black = (long) 0;
		Long white = (long) 1;
  		
		Couleur mCoulBlack = new Couleur(black, "Noir");
  		Couleur mCoulWhite = new Couleur(white,"Blanc");
 		
 		setX(colonne);
 		
 		setY(ligne);
 		
 		setPartie(game);
 		
 		if (ligne == 0 || ligne == 1) {
 			
 			setPionCase(new Pion(mIdPiece, mName, mCoulWhite));
 			
 			setEtat(false);
 		}
 		else if (ligne == 6 || ligne == 7){
 			
 			setPionCase(new Pion(mIdPiece, mName, mCoulBlack));
 			
 			setEtat(false);
 			
 		}
 		else {
 			
 			setPionCase(null);
 			
 			setEtat(true);
 		}
 	}
}
