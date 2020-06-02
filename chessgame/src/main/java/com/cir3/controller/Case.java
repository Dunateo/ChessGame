package com.cir3.controller;

class Case {
	
	private int id;
	private boolean status;
	private boolean color;
	private Piece maPiece;
	private int x;
	private int y;
	
	// Getter & Setter id
	public int getId() {
		return id;
	}
	
	public void setId(int mId) {
		id = mId;
	}
	
	// Getter & Setter status
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean mStatus) {
		status = mStatus;
	}
	
	// Getter & Setter color
	public boolean getColor() {
		return color;
	}
	
	public void setColor(boolean mColor) {
		color = mColor;
	}
	
	// Getter & Setter piece
	public Piece getPiece() {
		return maPiece;
	}
	
	public void setPiece(Piece mPiece) {
		maPiece = mPiece;
	}
	
	// Getter & Setter x
	public int getX() {
		return x;
	}
	
	public void setX(int mX) {
		x = mX;
	}
	
	// Getter & Setter y
	public int getY() {
		return y;
	}
	
	public void setY(int mY) {
		y = mY;
	}
	
	// Devine la couleur de la case en fonction de sa position
	public boolean guessColor(int x, int y) {
		
		boolean ligne = false;
		boolean colonne = false;
		boolean color = false;
		
		x++;
		y++;
		
		if(y%2 == 0)
			ligne = true;
		else
			ligne = false;
		
		if(x%2 == 0)
			colonne = true;
		else
			colonne = false;
		
		if(ligne == true && colonne == true) 
			color = false;
		else if(ligne == false && colonne == false)
			color = false;
		else
			color = true;
		
		return color;
	}
	
	// Creer une case vide
	public void createCaseVide(int ligne, int colonne, int mIdCase) {
		
		Case mCase = null;
		Piece tPiece = null;
		boolean tColor = false;
		
		mCase.setId(mIdCase);
		
		mCase.setPiece(tPiece);
		
		tColor = guessColor(ligne,colonne);
		
		mCase.setColor(tColor);
		
		mCase.setStatus(true);
		
		mCase.setX(colonne);
		
		mCase.setY(ligne);
	}
	
	// Creer une case avec une piece complexe
	public void createCase(int ligne, int colonne, int mIdCase, int mIdPiece, char mName) {
		
		Case mCase = null;
		Piece tPiece = null;
		boolean tColor = false;
		
		mCase.setId(mIdCase);
		
		tPiece.createPiece(mIdPiece, mName, ligne);
		
		mCase.setPiece(tPiece);
		
		tColor = guessColor(ligne,colonne);
		
		mCase.setColor(tColor);
		
		mCase.setStatus(false);
		
		mCase.setX(colonne);
		
		mCase.setY(ligne);
	}
	
	// Creer une case avec un pion
	public void createCasePion(int ligne, int colonne, int mIdCase, int mIdPiece) {
		
		Case mCase = null;
		Piece tPiece = null;
		boolean tColor = false;
		
		mCase.setId(mIdCase);
		
		tPiece.createPiece(mIdPiece, "Pion", ligne);
		
		mCase.setPiece(tPiece);
		
		tColor = guessColor(ligne,colonne);
		
		mCase.setColor(tColor);
		
		mCase.setStatus(false);
		
		mCase.setX(colonne);

		mCase.setY(ligne);
	}
};