package com.cir3.controller;

class Plateau {
	
	private int id;
	private Case mPlateau[8][8];
	private char listePieces[] = {"Tour","Cavalier","Fou","Roi","Reine","Fou","Cavalier","Tour"};
	
	// Getter & Setter id
	public int getId() {
		return id;
	}
	
	public void setId(int mId) {
		id = mId;
	}
	
	// Getter & Setter case
	public Case getCase(int x, int y) {
		return mPlateau[x][y];
	}
	
	public void setCase(int x, int y, Case mCase) {
		mPlateau[x][y] = mCase;
	}
	
	// Initialiser le plateau
	public void initPlateau() {
		
		int cptCase = 0;
		int cptPiece = 0;
		
		// On parcourt les cases du plateau
		for(int i = 0; i < 8; i++) {
			
			for(int j = 0; j < 8; j++) {
				
				cptCase++;
				
				// Creation des pieces de la premiere et derniere ligne
				if(i == 0 || i == 7) {
					
					cptPiece++;
					mPlateau[i][j].createCase(i,j,cptCase,cptPiece,listePieces[j]);
					
				}
				
				// Creation des 2 lignes de pions
				if(i == 1 || i == 6) {
					
					cptPiece++;
					mPlateau[i][j].createCasePion(i,j,cptCase,cptPiece);
					
				}
				
				// Creation des cases vides
				mPlateau[i][j].createCaseVide(i,j,cptCase);
			}
		}
	}
	
	// Rafraichir le plateau
	public void updatePlateau() {
		
		
		
	}

};