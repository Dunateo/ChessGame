package com.cir3.chessgame.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "pion")
public class Pion {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn( updatable = false)
    private Couleur couleur;

    @Lob
    @Column(nullable = false)
    private byte[] image;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
            },
            mappedBy = "pionCase")
    private List<Cases> cases = new ArrayList<>();

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

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public List<Cases> getCases() {
        return cases;
    }

    public void setCases(List<Cases> cases) {
        this.cases = cases;
    }
    
    // Creer un pion
 	public Pion(Long mIdPiece, String mName, Couleur mCoul) {
 		
 		setId(mIdPiece);
 		
 		setNom(mName);
 		
 		setCouleur(mCoul);
 	}
 	
 	// Creer un pion
  	public void createPion(Long mIdPiece, String mName, Couleur mCoul) {
  		
  		setId(mIdPiece);
  		
  		setNom(mName);
  		
  		setCouleur(mCoul);
  	}
}
