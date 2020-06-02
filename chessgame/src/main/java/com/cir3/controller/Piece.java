package com.cir3.controller;

class Piece {
	
	private int id;
	private boolean color;
	private char name;
	
	// Getter & Setter name
	public char getName() {
		return name;
	}
	
	public void setName(char mNom) {
		name = mNom;
	}
	
	// Getter & Setter id
	public int getId() {
		return id;
	}
	
	public void setId(int mId) {
		id = mId;
	}
	
	// Getter & Setter color
	public boolean getColor() {
		return color;
	}
	
	public void setColor(boolean mColor) {
		color = mColor;
	}
	
	// Creer une piece
	public void createPiece(Piece mIdPiece, char mName, int ligne) {
		
		Piece mPiece = null;
		
		mPiece.setId(mIdPiece);
		
		mPiece.setName(mName);
		
		if(ligne == 7 || ligne == 6)
			mPiece.setColor(false);
		else
			mPiece.setColor(true);
	}
};