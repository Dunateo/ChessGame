package com.cir3.controller;

class Plateau {
	
	private int id;
	private Case mPlateau[8][8];
	private char listePieces[] = {"Tour","Cavalier","Fou","Roi","Reine","Fou","Cavalier","Tour"};
	private char infos[254];
	
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
	
	// Verifier le deplacement du pion
	public boolean checkPion(int myX, int myY, int nX, int nY) {
		
		boolean move = false;
		
		if(nX == myX && nY == myY++) {
			move == true;
		}
		else if (nX == myX++ && nY == myY++ && mPlateau[8][8].getCase(myX,myY).getStatus() == false && mPlateau[8][8].getCase(myX,myY).getPiece().getColor() != mPlateau[8][8].getCase(nX,nY).getPiece().getColor()) {
			move == true;
		}
		
		return move;
	}
	
	// Verifier le deplacement de la tour
	public boolean checkTour(int myX, int myY, int nX, int nY) {
		
		boolean move = false;
		
		if (myY == nY && myX != nX) {
			
			if (myX < nX) {
				
				for(int i = myX; i < nX; i++) {
					
					if (mPlateau[8][8].getCase(i,myY).getStatus() == false && i != nX-1) {
						
						move = false;
						break;
						
					}
					else if (mPlateau[8][8].getCase(i,myY).getStatus() == false && i == nX-1) {
						
						move = true;
						break;
						
					}
					else if (mPlateau[8][8].getCase(i,myY).getStatus() == true && i == nX-1) {
						
						move = true;
						break;
						
					}
				}
			}
			else {
				
				for(int i = myX; i > nX; i--) {
					
					if (mPlateau[8][8].getCase(i,myY).getStatus() == false && i != nX+1) {
						
						move = false;
						break;
						
					}
					else if (mPlateau[8][8].getCase(i,myY).getStatus() == false && i == nX+1) {
						
						move = true;
						break;
						
					}
					else if (mPlateau[8][8].getCase(i,myY).getStatus() == true && i == nX+1) {
						
						move = true;
						break;
						
					}
				}
			}
		}
		else if (myY != nY && myX == nX) {
			
			if (myY < nY) {
				
				for(int i = myY; i < nY; i++) {
					
					if (mPlateau[8][8].getCase(myX,i).getStatus() == false && i != nY-1) {
						
						move = false;
						break;
						
					}
					else if (mPlateau[8][8].getCase(myX,i).getStatus() == false && i == nY-1) {
						
						move = true;
						break;
						
					}
					else if (mPlateau[8][8].getCase(myX,i).getStatus() == true && i == nY-1) {
						
						move = true;
						break;
						
					}
					
				}
			}
			else {
				
				for(int i = myY; i > nY; i--) {
					
					if (mPlateau[8][8].getCase(myX,i).getStatus() == false && i != nY+1) {
						
						move = false;
						break;
						
					}
					else if (mPlateau[8][8].getCase(myX,i).getStatus() == false && i == nY+1) {
						
						move = true;
						break;
						
					}
					else if (mPlateau[8][8].getCase(myX,i).getStatus() == true && i == nY+1) {
						
						move = true;
						break;
						
					}
				}
			}
		}
		
		
		if(nX == myX && nY == myY++) {
			move == true;
		}
		else if (nX == myX++ && nY == myY++ && mPlateau[8][8].getCase(myX,myY).getStatus() == false && mPlateau[8][8].getCase(myX,myY).getPiece().getColor() != mPlateau[8][8].getCase(nX,nY).getPiece().getColor()) {
			move == true;
		}
		
		return move;
	}
	
	// Verifier le deplacement de la piece
	public boolean checkMove(int myX, int myY, int nX, int nY) {
		
		boolean move = false;
		
		char name[15] = mPlateau[8][8].getCase(myX,myY).getPiece().getName();
		
		switch(name) {
		
		case Pion:
			checkPion(myX,myY,nX,nY);
			break;
			
		case Tour:
			
			break;
			
		case Cavalier:
			
			break;
			
		case Fou:
			
			break;
			
		case Roi:
			
			break;
			
		case Reine:
			
			break;
		}
		
		if (move) {
			
			updatePlateau();
			
		}
		
	}

};