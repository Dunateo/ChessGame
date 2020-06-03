package com.cir3.chessgame.domain;

import org.springframework.boot.autoconfigure.batch.JobExecutionEvent;

import javax.persistence.*;
import java.util.*;

@Entity(name = "partie")
public class Partie {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean etat;

    @Column
    private int Tour;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date fixtemps;

    @Column
    private int duree;

    public Partie() {
		
	}


	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Joueur joueurNoir;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(  updatable = false)
    private List<Joueur> joueur = new ArrayList<Joueur>();


    @OneToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
            },
            mappedBy = "partie")
    private List<Cases> table = new ArrayList<>();


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public boolean isEtat() {
		return etat;
	}


	public void setEtat(boolean etat) {
		this.etat = etat;
	}


	public int getTour() {
		return Tour;
	}


	public void setTour(int tour) {
		Tour = tour;
	}


	public Date getFixtemps() {
		return fixtemps;
	}


	public void setFixtemps(Date fixtemps) {
		this.fixtemps = fixtemps;
	}


	public int getDuree() {
		return duree;
	}


	public void setDuree(int duree) {
		this.duree = duree;
	}


	public Joueur getJoueurNoir() {
		return joueurNoir;
	}


	public void setJoueurNoir(Joueur joueurNoir) {
		this.joueurNoir = joueurNoir;
	}


	public List<Joueur> getJoueur() {
		return joueur;
	}


	public void setJoueur(List<Joueur> joueur) {
		this.joueur = joueur;
	}
	
	public void AddJoueur (Joueur j) {
		this.joueur.add(j);
	}


	public List<Cases> getTable() {
		return table;
	}


	public void setTable(List<Cases> table) {
		this.table = table;
	}


	public Partie(Long id, boolean etat, int tour) {
		
		this.id = id;
		this.etat = etat;
		Tour = tour;
	}

}
