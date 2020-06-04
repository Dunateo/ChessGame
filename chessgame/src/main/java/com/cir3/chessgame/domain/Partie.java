package com.cir3.chessgame.domain;

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

    public Partie() {
		
	}
    
    public Partie(Joueur j1,Joueur j2) {
		etat=false;
		Tour=-1;
		duree=0;
		joueurNoir=j1;
		joueur.add(j1);
		joueur.add(j2);
		
	}
    
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
	
	public void createPartie(Long myId) {
    	
		String listePieces[] = {"Tour","Cavalier","Fou","Roi","Reine","Fou","Cavalier","Tour"};
		
		setEtat(true);
		
		setDuree(0);
		
		setTour(0);
		
		// On parcourt les cases du plateau
		for(int i = 0; i < 8; i++) {
			
			for(int j = 0; j < 8; j++) {
				
				
				// Creation des pieces de la premiere et derniere ligne
				if(i == 0 || i == 7) {
					
					table.add(new Cases(i,j,listePieces[j],this));
					
				}
				// Creation des 2 lignes de pions
				else if(i == 1 || i == 6) {
					
					table.add(new Cases(i,j,"Pion",this));
				}
				else {
				
					// Creation des cases vides
					table.add(new Cases(i,j,"",this));
				}
			}
		}
		
		System.out.println(this.getTable().get(5).getX());
    }
}
