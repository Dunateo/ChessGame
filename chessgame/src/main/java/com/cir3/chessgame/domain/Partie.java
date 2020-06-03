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
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Joueur joueurNoir;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(  updatable = false)
    private Set<Joueur> joueur;
    private List<Joueur> joueur = new ArrayList<Joueur>();


    @OneToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
            },
            mappedBy = "partie")
    private List<Cases> table = new ArrayList<>();

<<<<<<< HEAD
=======

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
>>>>>>> jejebranch
    
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


<<<<<<< HEAD
	public Set<Joueur> getJoueur() {
=======
	public List<Joueur> getJoueur() {
>>>>>>> jejebranch
		return joueur;
	}


<<<<<<< HEAD
	public void setJoueur(Set<Joueur> joueur) {
		this.joueur = joueur;
	}
=======
	public void setJoueur(List<Joueur> joueur) {
		this.joueur = joueur;
	}
	
	public void AddJoueur (Joueur j) {
		this.joueur.add(j);
	}
>>>>>>> jejebranch


	public List<Cases> getTable() {
		return table;
	}


	public void setTable(List<Cases> table) {
		this.table = table;
	}
<<<<<<< HEAD
	
	public Partie(Long myId) {
    	
    	Long cptCase = (long) 0;
		
    	Long cptPiece = (long) 0;
		
		Cases mPlateau[][] =  {
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null}
				};
		
		String listePieces[] = {"Tour","Cavalier","Fou","Roi","Reine","Fou","Cavalier","Tour"};
		
		setEtat(false);
		
		setId(myId);
		
		setDuree(0);
		
		setTour(0);
		
		// On parcourt les cases du plateau
		for(int i = 0; i < 8; i++) {
			
			for(int j = 0; j < 8; j++) {
				
				cptCase++;
				
				// Creation des pieces de la premiere et derniere ligne
				if(i == 0 || i == 7) {
					
					cptPiece++;
					mPlateau[i][j] = new Cases(i,j,cptCase,cptPiece,listePieces[j]);
					
				}
				
				// Creation des 2 lignes de pions
				if(i == 1 || i == 6) {
					
					cptPiece++;
					mPlateau[i][j] = new Cases(i,j,cptCase,cptPiece,"Pion");
					
				}
				
				// Creation des cases vides
				mPlateau[i][j] = new Cases(i,j,cptCase,cptPiece,"");
			}
		}
		
		for(Cases n : table) {
			
			for(int i = 0; i < 8; i++) {
				
				for(int j = 0; j < 8; j++) {
					
					n = mPlateau[i][j];
					
				}
			}
		}
		
		setTable(table);
    }
=======

>>>>>>> jejebranch
}
