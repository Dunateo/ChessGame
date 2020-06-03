package com.cir3.chessgame.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "couleur")
public class Couleur {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
            },
            mappedBy = "couleur")
    private List<Pion> pions = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Pion> getPions() {
        return pions;
    }

    public void setPions(List<Pion> pions) {
        this.pions = pions;
    }
    
    public Couleur(Long myId, String myName) {
    	
    	setId(myId);
    	setNom(myName);
    }
    
    public boolean diffCoul(Couleur myCoul, Couleur newCoul) {
    	
    	if (myCoul.getNom() != newCoul.getNom())
    		return true;
    	else
    		return false;
    }
    
    public Couleur returnOtherCoul(Couleur myCoul) {
    	
    	Long black = (long) 0;
		Long white = (long) 1;
    	
    	if (myCoul.getNom() == "Noir")
    		return new Couleur(white,"Blanc");
    	else
    		return new Couleur(black, "Noir");
    }
}
