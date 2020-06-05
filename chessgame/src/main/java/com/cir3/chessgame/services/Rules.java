package com.cir3.chessgame.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cir3.chessgame.domain.Cases;
import com.cir3.chessgame.domain.Couleur;
import com.cir3.chessgame.domain.Partie;
import com.cir3.chessgame.domain.Pion;
import com.cir3.chessgame.repository.CasesRepository;
import com.cir3.chessgame.repository.CouleurRepository;
import com.cir3.chessgame.repository.PartieRepository;
import com.cir3.chessgame.repository.PionRepository;

@Service
public class Rules {
	
	protected final PartieRepository games;
	
	protected final CasesRepository Case;
	
	protected final CouleurRepository Couleurs;
	
	protected final PionRepository Pions;
	
	@Autowired
	public Rules(PartieRepository games, CasesRepository Case, CouleurRepository Couleurs, PionRepository Pions) {
		
		this.games = games;
		this.Case = Case;
		this.Couleurs = Couleurs;
		this.Pions = Pions;
	}
	
	// Renvoyer la couleur inverse
	public Couleur otherCoul(Couleur myCoul) {
	    
    	if (myCoul.getNom().equals("Noir"))
    		return Couleurs.findById((long) 1).get();
    	else
    		return Couleurs.findById((long) 2).get();
    }
	
	// Verifier si la piece donnee est le roi
	public boolean verifRoi(Cases myCase) {
		
		if (myCase.getPionCase() != null) {
			if (myCase.getPionCase().getId() == 4 || myCase.getPionCase().getId() == 10)
				return true;
			else
				return false;
		}
		else {
			return false;
		}
	}
	
	// Donner a chaque case sa piece correspondante
	public void givePiece(Long myId) {
		
		List<Cases> g = games.findById(myId).get().getTable();
		
		for(int i = 0; i < 64; i++) {
			
			// Pions Blancs
			if (g.get(i).getY() == 1)
				g.get(i).setPionCase(Pions.findById((long) 6).get());
			// Pions Noirs
			else if (g.get(i).getY() == 6)
				g.get(i).setPionCase(Pions.findById((long) 12).get());
			// Tours Blanches
			else if (g.get(i).getY() == 0 && (g.get(i).getX() == 0 || g.get(i).getX() == 7))
				g.get(i).setPionCase(Pions.findById((long) 1).get());
			// Tours Noires
			else if (g.get(i).getY() == 7 && (g.get(i).getX() == 0 || g.get(i).getX() == 7))
				g.get(i).setPionCase(Pions.findById((long) 7).get());
			// Cavalier Blancs
			else if (g.get(i).getY() == 0 && (g.get(i).getX() == 1 || g.get(i).getX() == 6))
				g.get(i).setPionCase(Pions.findById((long) 2).get());
			// Cavaliers Noirs
			else if (g.get(i).getY() == 7 && (g.get(i).getX() == 1 || g.get(i).getX() == 6))
				g.get(i).setPionCase(Pions.findById((long) 8).get());
			// Fous Blancs
			else if (g.get(i).getY() == 0 && (g.get(i).getX() == 2 || g.get(i).getX() == 5))
				g.get(i).setPionCase(Pions.findById((long) 3).get());
			// Fous Noirs
			else if (g.get(i).getY() == 7 && (g.get(i).getX() == 2 || g.get(i).getX() == 5))
				g.get(i).setPionCase(Pions.findById((long) 9).get());
			// Roi Blanc
			else if (g.get(i).getY() == 0 && g.get(i).getX() == 3)
				g.get(i).setPionCase(Pions.findById((long) 4).get());
			// Roi Noir
			else if (g.get(i).getY() == 7 && g.get(i).getX() == 3)
				g.get(i).setPionCase(Pions.findById((long) 10).get());
			// Reine Blanche
			else if (g.get(i).getY() == 0 && g.get(i).getX() == 4)
				g.get(i).setPionCase(Pions.findById((long) 5).get());
			// Reine Noire
			else if (g.get(i).getY() == 7 && g.get(i).getX() == 4)
				g.get(i).setPionCase(Pions.findById((long) 11).get());
		}
	}
	
	// Donner a chaque piece sa couleur correspondante
	public void giveCouleur(Long myId) {
		
		List<Cases> g = games.findById(myId).get().getTable();
		
		for(int i = 0; i < 64; i++) {
			
			if (g.get(i).getY() == 0 || g.get(i).getY() == 1)
				g.get(i).getPionCase().setCouleur(Couleurs.findById((long) 1).get());
			else if (g.get(i).getY() == 6 || g.get(i).getY() == 7)
				g.get(i).getPionCase().setCouleur(Couleurs.findById((long) 2).get());
		}
	}
	
	// Verifier le deplacement du pion
  	public boolean checkPion(Cases myCase, int nX, int nY, Long myId) {
  		
  		boolean move = false;
  		
  		
  		Couleur myCoul = myCase.getPionCase().getCouleur();
  		
  		int myX = myCase.getX();
  		int myY = myCase.getY();
  		
  		List<Cases> g = games.findById(myId).get().getTable();
  		
  		if(myCase.getPionCase().getCouleur().getNom().equals("Blanc")) {
  			
  			Couleur newCoulHG = null;
  	  		Couleur newCoulHD = null;
  			
  			boolean statusH = false;
  	  		boolean statusHH = false;
  	  		boolean statusHG = false;
  	  		boolean statusHD = false;
  			
  			// Verification presence piece
  			for(int i = 0; i < 64; i++) {
  				
  				if (g.get(i).getX() == myCase.getX() && g.get(i).getY() == myCase.getY()+1)
  					statusH = g.get(i).isEtat();
  				
  				if (g.get(i).getX() == myCase.getX() && g.get(i).getY() == myCase.getY()+2)
  					statusHH = g.get(i).isEtat();
  				
  				if (g.get(i).getX() == myCase.getX()+1 && g.get(i).getY() == myCase.getY()+1)
  					statusHD = g.get(i).isEtat();
  				
  				if (g.get(i).getX() == myCase.getX()-1 && g.get(i).getY() == myCase.getY()+1)
  					statusHG = g.get(i).isEtat();
  			}
  	  		
  			// Verification couleur piece 1
  	  		if (statusHG) {
  	  			
  	  			for(int i = 0; i < 64; i++) {
  	  				
  	  				if (g.get(i).getX() == myCase.getX()-1 && g.get(i).getY() == myCase.getY()+1)
  	  					newCoulHG = g.get(i).getPionCase().getCouleur();
  	  			}
  	  		}
  	  		
  	  		// Verification couleur piece 2
  	  		if (statusHD) {
  	  			
  	  			for(int i = 0; i < 64; i++) {
  	  				
  	  				if (g.get(i).getX() == myCase.getX()+1 && g.get(i).getY() == myCase.getY()+1)
  	  					newCoulHD = g.get(i).getPionCase().getCouleur();
  	  			}
  	  		}
  	  		
  	  		// Verification mouvement
  	  		if(statusH == false) {
  	  			
	  	  		int testY = myY+1;
	  	  		
	  	  		if (nX == myX && nY == testY)
	  	  			move = true;
  	  		}
  	  		
  	  		if (statusHH == false && myY == 1) {
  	  			
  	  			int testY = myY+2;
  	  			
  	  			if (nX == myX && nY == testY)
  	  				move = true;
  	  		}
  	  		
  	  		if (statusHD == true && newCoulHD.getId() != myCoul.getId() && verifRoi(returnCaseCoords(nX,nY,myId)) == false) {
  	  			
	  	  		int testX = myX+1;
	  	  		int testY = myY+1;
  	  			
	  	  		if(nX == testX && nY == testY)
	  	  			move = true;
  	  		}
  	  		
  	  		if (statusHG == true && newCoulHG.getId() != myCoul.getId() && verifRoi(returnCaseCoords(nX,nY,myId)) == false) {
  	  			
	  	  		int testX = myX-1;
	  	  		int testY = myY+1;
		  			
	  	  		if(nX == testX && nY == testY)
	  	  			move = true;
  	  		}
  		}
  		else {
  			
  			Couleur newCoulBG = null;
  	  		Couleur newCoulBD = null;
  			
  			boolean statusB = false;
  	  		boolean statusBB = false;
  	  		boolean statusBG = false;
  	  		boolean statusBD = false;
  			
  			// Verification presence piece
  			for(int i = 0; i < 64; i++) {
  				
  				if (g.get(i).getX() == myCase.getX() && g.get(i).getY() == myCase.getY()-1)
  					statusB = g.get(i).isEtat();
  				
  				if (g.get(i).getX() == myCase.getX() && g.get(i).getY() == myCase.getY()-2)
  					statusBB = g.get(i).isEtat();
  				
  				if (g.get(i).getX() == myCase.getX()+1 && g.get(i).getY() == myCase.getY()-1)
  					statusBD = g.get(i).isEtat();
  				
  				if (g.get(i).getX() == myCase.getX()-1 && g.get(i).getY() == myCase.getY()-1)
  					statusBG = g.get(i).isEtat();
  			}
  			
  			// Verification couleur piece 1
  	  		if (statusBG) {
  	  			
  	  			for(int i = 0; i < 64; i++) {
  	  				
  	  				if (g.get(i).getX() == myCase.getX()-1 && g.get(i).getY() == myCase.getY()-1)
  	  					newCoulBG = g.get(i).getPionCase().getCouleur();
  	  			}
  	  		}
  	  		
  	  		// Verification couleur piece 2
  	  		if (statusBD) {
  	  			
  	  			for(int i = 0; i < 64; i++) {
  	  				
  	  				if (g.get(i).getX() == myCase.getX()+1 && g.get(i).getY() == myCase.getY()-1)
  	  					newCoulBD = g.get(i).getPionCase().getCouleur();
  	  			}
  	  		}
  	  		
  	  		
  	  		
  	  		// Verification mouvement
  	  		if(statusB == false) {
  	  			
  	  			int testY = myY-1;
  	  			
  	  			if (nX == myX && nY == testY)
  	  				move = true;
  	  		}
  	  		
  	  		if (statusBB == false && myY == 6) {
  	  			
  	  			int testY = myY-2;
  	  			
  	  			if (nX == myX && nY == testY)
  	  				move = true;
  	  		}
  	  		
  	  		if (statusBD == true && !newCoulBD.getNom().equals(myCoul.getNom()) && verifRoi(returnCaseCoords(nX,nY,myId)) == false) {
  	  			
	  	  		int testX = myX+1;
	  	  		int testY = myY-1;
	  	  		
	  	  		if (nX == testX && nY == testY)
	  	  			move = true;
  	  		}
  	  		
  	  		if (statusBG == true && !newCoulBG.getNom().equals(myCoul.getNom()) && verifRoi(returnCaseCoords(nX,nY,myId)) == false) {
  	  			
	  	  		int testX = myX-1;
	  	  		int testY = myY-1;
	  	  		
		  	  	if (nX == testX && nY == testY)
	  	  			move = true;
  	  		}
  		}
  		
  		return move;
  	}
  	
  	// Verifier le deplacement de la tour
 	public boolean checkTour(Cases myCase, int nX, int nY, Long myId) {
 		
 		boolean move = false;
 		
 		Couleur newCoul = null;
  		Couleur myCoul = myCase.getPionCase().getCouleur();
 		
  		int myX = myCase.getX();
  		int myY = myCase.getY();
  		
  		List<Cases> g = games.findById(myId).get().getTable();
  		
  		boolean status = false;
 		
  		// Deplacement horizontal
 		if (myY == nY && myX != nX) {
 			
 			// Deplacement vers la droite
 			if (myX < nX && myX < 7) {
 			
 				if (nX-myX == 1) {
 					
 					for(int i = 0; i < (nX-myX)+1; i++) {
 	 					
 	 					Cases caseTemp = returnCaseCoords(myCase.getX()+i,myCase.getY(),myId);
 	 					
 	 					status = g.get(g.indexOf(caseTemp)).isEtat();
 	 				}
 	 				
 					// Verification couleur piece
 					if (status) {
 						
 						Cases caseTemp = returnCaseCoords(myCase.getX()+(nX-myX),myCase.getY(),myId);
 						newCoul = g.get(g.indexOf(caseTemp)).getPionCase().getCouleur();
 					}
 					
 					// Verification mouvement
 					if (status == false) {
 						
 						move = true;
 					}
 					if (status == true && newCoul.getId() != myCoul.getId() && verifRoi(returnCaseCoords(nX,nY,myId)) == false) {
 						
 						move = true;
 					}
 				}
 				else {
 					
 					for(int i = 1; i <= (nX-myX); i++) {
 	 					
 	 					Cases caseTemp = returnCaseCoords(myCase.getX()+i,myCase.getY(),myId);
 	 					
 	 					status = g.get(g.indexOf(caseTemp)).isEtat();
 	 					
 	 					if (status == true && i < (nX-myX))
 							return false;
 	 				}
 	 				
 					// Verification couleur piece
 					if (status) {
 						
 						Cases caseTemp = returnCaseCoords(myCase.getX()+(nX-myX),myCase.getY(),myId);
 						newCoul = g.get(g.indexOf(caseTemp)).getPionCase().getCouleur();
 					}
 	 				
 					// Verification mouvement
 					if (status == false) {
 						
 						move = true;
 					}
 					if (status == true && newCoul.getId() != myCoul.getId() && verifRoi(returnCaseCoords(nX,nY,myId)) == false) {
 						
 						move = true;
 					}
 				}
 			}
 			// Deplacement vers la gauche
 			else if (myX > nX && myX > 0) {
 				
 				if (myX-nX == 1) {
 					
 					// Verification presence piece
 					for(int i = 0; i < (myX-nX)+1; i++) {
 	 					
 	 					Cases caseTemp = returnCaseCoords(myCase.getX()-i,myCase.getY(),myId);
 	 					
 	 					status = g.get(g.indexOf(caseTemp)).isEtat();
 	 				}
 	 				
 					// Verification couleur piece
 					if (status) {
 						
 						Cases caseTemp = returnCaseCoords(myCase.getX()-(myX-nX),myCase.getY(),myId);
 						newCoul = g.get(g.indexOf(caseTemp)).getPionCase().getCouleur();
 					}
 	 				
 					// Verification mouvement
 					if (status == false) {
 						
 						move = true;
 					}
 					if (status == true && newCoul.getId() != myCoul.getId() && verifRoi(returnCaseCoords(nX,nY,myId)) == false) {
 						
 						move = true;
 					}
 				}
 				else {
 					
 					// Verification presence piece
 					for(int i = 1; i <= (myX-nX); i++) {
 	 					
 	 					Cases caseTemp = returnCaseCoords(myCase.getX()-i,myCase.getY(),myId);
 	 					
 	 					status = g.get(g.indexOf(caseTemp)).isEtat();
 	 					
 	 					if (status == true && i < (myX-nX))
 							return false;
 	 				}
 					
 					// Verification couleur piece
 					if (status) {
 						
 						Cases caseTemp = returnCaseCoords(myCase.getX()-(myX-nX),myCase.getY(),myId);
 						newCoul = g.get(g.indexOf(caseTemp)).getPionCase().getCouleur();
 					}
 					
 					// Verification mouvement
 					if (status == false) {
 						
 						move = true;
 					}
 					if (status == true && newCoul.getId() != myCoul.getId() && verifRoi(returnCaseCoords(nX,nY,myId)) == false) {
 						
 						move = true;
 					}
 				}
 			}
 		}
 		// Deplacement vertical
 		else if (myY != nY && myX == nX) {
 			
 			// Deplacement vers le haut
 			if (myY < nY && myY < 7) {
 				
 				if (nY-myY == 1) {
 					
 					// Verification presence piece
 					for(int i = 0; i < (nY-myY)+1; i++) {
 	 					
 	 					Cases caseTemp = returnCaseCoords(myCase.getX(),myCase.getY()+i,myId);
 	 					
 	 					status = g.get(g.indexOf(caseTemp)).isEtat();
 	 				}
 	 				
 					// Verification couleur piece
 					if (status) {
 						
 						Cases caseTemp = returnCaseCoords(myCase.getX(),myCase.getY()+(nY-myY),myId);
 						newCoul = g.get(g.indexOf(caseTemp)).getPionCase().getCouleur();
 					}
 	 				
 					// Verification mouvement
 					if (status == false) {
 						
 						move = true;
 					}
 					if (status == true && newCoul.getId() != myCoul.getId() && verifRoi(returnCaseCoords(nX,nY,myId)) == false) {
 						
 						move = true;
 					}
 				}
 				else {
 					
 					// Verification presence piece
 					for(int i = 1; i <= (nY-myY); i++) {
 	 					
 	 					Cases caseTemp = returnCaseCoords(myCase.getX(),myCase.getY()+i,myId);
 	 					
 	 					status = g.get(g.indexOf(caseTemp)).isEtat();
 	 					
 	 					if (status == true && i < (nY-myY)-1)
 							return false;
 	 				}
 					
 					// Verification couleur piece
 					if (status) {
 						
 						Cases caseTemp = returnCaseCoords(myCase.getX(),myCase.getY()+(nY-myY),myId);
 						newCoul = g.get(g.indexOf(caseTemp)).getPionCase().getCouleur();
 					}
 					
 					// Verification mouvement
 					if (status == false) {
 						
 						move = true;
 					}
 					if (status == true && newCoul.getId() != myCoul.getId() && verifRoi(returnCaseCoords(nX,nY,myId)) == false) {
 						
 						move = true;
 					}
 				}
 			}
 			// Deplacement vers le bas
 			else if (myY > nY && myY > 0) {
 				
 				if (myY-nY == 1) {
 					
 					// Verification presence piece
 					for(int i = 0; i < (myY-nY)+1; i++) {
 	 					
 	 					Cases caseTemp = returnCaseCoords(myCase.getX(),myCase.getY()-i,myId);
 	 					
 	 					status = g.get(g.indexOf(caseTemp)).isEtat();
 	 				}
 	 				
 					// Verification couleur piece
 					if (status) {
 						
 						Cases caseTemp = returnCaseCoords(myCase.getX(),myCase.getY()-(myY-nY),myId);
 						newCoul = g.get(g.indexOf(caseTemp)).getPionCase().getCouleur();
 					}
 	 				
 					// Verification mouvement
 					if (status == false) {
 						
 						move = true;
 					}
 					if (status == true && newCoul.getId() != myCoul.getId() && verifRoi(returnCaseCoords(nX,nY,myId)) == false) {
 						
 						move = true;
 					}
 				}
 				else {
 					
 					// Verification presence piece
 					for(int i = 1; i <= (myY-nY); i++) {
 	 					
 	 					Cases caseTemp = returnCaseCoords(myCase.getX(),myCase.getY()-i,myId);
 	 					
 	 					status = g.get(g.indexOf(caseTemp)).isEtat();
 	 					
 	 					if (status == true && i < (myY-nY)-1)
 							return false;
 	 				}
 					
 					// Verification couleur piece
 					if (status) {
 						
 						Cases caseTemp = returnCaseCoords(myCase.getX(),myCase.getY()-(myY-nY),myId);
 						newCoul = g.get(g.indexOf(caseTemp)).getPionCase().getCouleur();
 					}
 					
 					// Verification mouvement
 					if (status == false) {
 						
 						move = true;
 					}
 					if (status == true && newCoul.getId() != myCoul.getId() && verifRoi(returnCaseCoords(nX,nY,myId)) == false) {
 						
 						move = true;
 					}
 				}
 			}
 		}
 		
 		return move;
 	}
 		
	// Verifier le deplacement du fou
 	public boolean checkFou(Cases myCase, int nX, int nY, Long myId) {
 		
 		boolean move = false;
 		
 		Couleur newCoul = null;
  		Couleur myCoul = myCase.getPionCase().getCouleur();
 		
  		int myX = myCase.getX();
  		int myY = myCase.getY();
  		
  		List<Cases> g = games.findById(myId).get().getTable();
  		
  		boolean status = false;
 		
  		// Deplacement vers le haut
 		if (myY < nY) {
 			
 			// Deplacement vers la droite
 			if (myX < nX && myX < 7) {
 				
 				if (nX-myX == 1) {
 					
 					// Verification presence piece
 					for(int i = 0; i < (nX-myX) + 1; i++) {
	 					
 	 					Cases caseTemp = returnCaseCoords(myCase.getX()+i,myCase.getY()+i,myId);
 	 					
 	 					status = g.get(g.indexOf(caseTemp)).isEtat();
 	 				}
 	 				
 					// Verification couleur piece
 					if (status) {
 						
 						Cases caseTemp = returnCaseCoords(myCase.getX()+(nX-myX),myCase.getY()+(nX-myX),myId);
 						newCoul = g.get(g.indexOf(caseTemp)).getPionCase().getCouleur();
 					}
 	 				
 					// Verification mouvement
 					if (status == false) {
 						
 						move = true;
 					}
 					if (status == true && newCoul.getId() != myCoul.getId() && verifRoi(returnCaseCoords(nX,nY,myId)) == false) {
 						
 						move = true;
 					}
 				}
 				else {
 					
 					// Verification presence piece
 					for(int i = 1; i < (nX-myX); i++) {
	 					
 	 					Cases caseTemp = returnCaseCoords(myCase.getX()+i,myCase.getY()+i,myId);
 	 					
 	 					status = g.get(g.indexOf(caseTemp)).isEtat();
 	 					
 	 					if (status == true && i < (nX-myX))
 							return false;
 	 				}
 	 				
 					// Verification couleur piece
 					if (status) {
 						
 						Cases caseTemp = returnCaseCoords(myCase.getX()+(nX-myX),myCase.getY()+(nX-myX),myId);
 						newCoul = g.get(g.indexOf(caseTemp)).getPionCase().getCouleur();
 					}
 	 				
 					// Verification mouvement
 					if (status == false) {
 						
 						move = true;
 					}
 					if (status == true && newCoul.getId() != myCoul.getId() && verifRoi(returnCaseCoords(nX,nY,myId)) == false) {
 						
 						move = true;
 					}
 				}
 			}
 			// Deplacement vers la gauche
 			else if (myX > nX && myX > 0) {
 				
 				if (myX-nX == 1) {
 					
 					// Verification presence piece
 					for(int i = 0; i < (myX-nX) + 1; i++) {
	 					
 	 					Cases caseTemp = returnCaseCoords(myCase.getX()-i,myCase.getY()+i,myId);
 	 					
 	 					status = g.get(g.indexOf(caseTemp)).isEtat();
 	 				}
 	 				
 					// Verification couleur piece
 					if (status) {
 						
 						Cases caseTemp = returnCaseCoords(myCase.getX()-(myX-nX),myCase.getY()+(myX-nX),myId);
 						newCoul = g.get(g.indexOf(caseTemp)).getPionCase().getCouleur();
 					}
 	 				
 					// Verification mouvement
 					if (status == false) {
 						
 						move = true;
 					}
 					if (status == true && newCoul.getId() != myCoul.getId() && verifRoi(returnCaseCoords(nX,nY,myId)) == false) {
 						
 						move = true;
 					}
 				}
 				else {
 					
 					// Verification presence piece
 					for(int i = 1; i < (myX-nX); i++) {
	 					
 	 					Cases caseTemp = returnCaseCoords(myCase.getX()-i,myCase.getY()+i,myId);
 	 					
 	 					status = g.get(g.indexOf(caseTemp)).isEtat();
 	 					
 	 					if (status == true && i < (myX-nX))
 							return false;
 	 				}
 	 				
 					// Verification couleur piece
 					if (status) {
 						
 						Cases caseTemp = returnCaseCoords(myCase.getX()-(myX-nX),myCase.getY()+(myX-nX),myId);
 						newCoul = g.get(g.indexOf(caseTemp)).getPionCase().getCouleur();
 					}
 	 				
 					// Verification mouvement
 					if (status == false) {
 						
 						move = true;
 					}
 					if (status == true && newCoul.getId() != myCoul.getId() && verifRoi(returnCaseCoords(nX,nY,myId)) == false) {
 						
 						move = true;
 					}
 				}
 			}
 		}
 		// Deplacement vers le bas
 		else if (myY > nY) {
 			
 			// Deplacement vers la droite
 			if (myX < nX && myX < 7) {
 				
 				if (nX-myX == 1) {
 					
 					// Verification presence piece
 					for(int i = 0; i < (nX-myX) + 1; i++) {
	 					
 	 					Cases caseTemp = returnCaseCoords(myCase.getX()+i,myCase.getY()-i,myId);
 	 					
 	 					status = g.get(g.indexOf(caseTemp)).isEtat();
 	 				}
 	 				
 					// Verification couleur piece
 					if (status) {
 						
 						Cases caseTemp = returnCaseCoords(myCase.getX()+(nX-myX),myCase.getY()-(nX-myX),myId);
 						newCoul = g.get(g.indexOf(caseTemp)).getPionCase().getCouleur();
 					}
 	 				
 					// Verification mouvement
 					if (status == false) {
 						
 						move = true;
 					}
 					if (status == true && newCoul.getId() != myCoul.getId() && verifRoi(returnCaseCoords(nX,nY,myId)) == false) {
 						
 						move = true;
 					}
 				}
 				else {
 					
 					// Verification presence piece
 					for(int i = 1; i < (nX-myX); i++) {
	 					
 	 					Cases caseTemp = returnCaseCoords(myCase.getX()+i,myCase.getY()-i,myId);
 	 					
 	 					status = g.get(g.indexOf(caseTemp)).isEtat();
 	 					
 	 					if (status == true && i < (nX-myX))
 							return false;
 	 				}
 	 				
 					// Verification couleur piece
 					if (status) {
 						
 						Cases caseTemp = returnCaseCoords(myCase.getX()+(nX-myX),myCase.getY()-(nX-myX),myId);
 						newCoul = g.get(g.indexOf(caseTemp)).getPionCase().getCouleur();
 					}
 	 				
 					// Verification mouvement
 					if (status == false) {
 						
 						move = true;
 					}
 					if (status == true && newCoul.getId() != myCoul.getId() && verifRoi(returnCaseCoords(nX,nY,myId)) == false) {
 						
 						move = true;
 					}
 				}
 			}
 			// Deplacement vers la gauche
 			else if (myX > nX && myX > 0) {
 				
 				if (myX-nX == 1) {
 					
 					// Verification presence piece
 					for(int i = 0; i < (myX-nX) + 1; i++) {
	 					
 	 					Cases caseTemp = returnCaseCoords(myCase.getX()-i,myCase.getY()-i,myId);
 	 					
 	 					status = g.get(g.indexOf(caseTemp)).isEtat();
 	 				}
 	 				
 					// Verification couleur piece
 					if (status) {
 						
 						Cases caseTemp = returnCaseCoords(myCase.getX()-(myX-nX),myCase.getY()-(myX-nX),myId);
 						newCoul = g.get(g.indexOf(caseTemp)).getPionCase().getCouleur();
 					}
 	 				
 					// Verification mouvement
 					if (status == false) {
 						
 						move = true;
 					}
 					if (status == true && newCoul.getId() != myCoul.getId() && verifRoi(returnCaseCoords(nX,nY,myId)) == false) {
 						
 						move = true;
 					}
 				}
 				else {
 					
 					// Verification presence piece
 					for(int i = 1; i < (myX-nX); i++) {
	 					
 	 					Cases caseTemp = returnCaseCoords(myCase.getX()-i,myCase.getY()-i,myId);
 	 					
 	 					status = g.get(g.indexOf(caseTemp)).isEtat();
 	 					
 	 					if (status == true && i < (myX-nX))
 							return false;
 	 				}
 	 				
 					// Verification couleur piece
 					if (status) {
 						
 						Cases caseTemp = returnCaseCoords(myCase.getX()-(myX-nX),myCase.getY()-(myX-nX),myId);
 						newCoul = g.get(g.indexOf(caseTemp)).getPionCase().getCouleur();
 					}
 	 				
 					// Verification mouvement
 					if (status == false) {
 						
 						move = true;
 					}
 					if (status == true && newCoul.getId() != myCoul.getId() && verifRoi(returnCaseCoords(nX,nY,myId)) == false) {
 						
 						move = true;
 					}
 				}
 			}
 		}
	
 		return move;
 	}
 	 
 	// Verifier le deplacement de la reine
  	public boolean checkReine(Cases myCase, int nX, int nY, Long myId) {
  		
  		boolean move = false;
  		
   		int myX = myCase.getX();
  		int myY = myCase.getY();
  		
   		// Deplacement horizontal
  		if (myY == nY && myX != nX) {
  			
  			move = checkTour(myCase,nX,nY,myId);
  		}
  		// Deplacement vertical
  		else if (myY != nY && myX == nX) {
  			
  			move = checkTour(myCase,nX,nY,myId);
  		}
  		else {
  			
  			move = checkFou(myCase,nX,nY,myId);
  		}
  		
  		return move;
  	}
 	 
  	// Verifier le deplacement du roi
   	public boolean checkRoi(Cases myCase, int nX, int nY, Long myId) {
   		
   		boolean move = false;
   		
   		Couleur newCoulH = null;
   		Couleur newCoulHG = null;
   		Couleur newCoulHD = null;
   		
   		Couleur newCoulB = null;
   		Couleur newCoulBG = null;
   		Couleur newCoulBD = null;
   		
   		Couleur myCoul = myCase.getPionCase().getCouleur();
   		
   		int myX = myCase.getX();
  		int myY = myCase.getY();
  		
  		List<Cases> g = games.findById(myId).get().getTable();
   		
  		boolean statusH = false;
   		boolean statusHG = false;
   		boolean statusHD = false;
   		
   		boolean statusB = false;
   		boolean statusBG = false;
   		boolean statusBD = false;
  		
  		// Verification presence piece
		for(int i = 0; i < 64; i++) {
			
			if (g.get(i).getX() == myCase.getX() && g.get(i).getY() == myCase.getY()+1)
				statusH = g.get(i).isEtat();
			
			if (g.get(i).getX() == myCase.getX()+1 && g.get(i).getY() == myCase.getY()+1)
				statusHD = g.get(i).isEtat();
			
			if (g.get(i).getX() == myCase.getX()-1 && g.get(i).getY() == myCase.getY()+1)
				statusHG = g.get(i).isEtat();
			
			if (g.get(i).getX() == myCase.getX()+1 && g.get(i).getY() == myCase.getY()-1)
				statusBD = g.get(i).isEtat();
			
			if (g.get(i).getX() == myCase.getX()-1 && g.get(i).getY() == myCase.getY()-1)
				statusBG = g.get(i).isEtat();
			
			if (g.get(i).getX() == myCase.getX() && g.get(i).getY() == myCase.getY()-1)
				statusB = g.get(i).isEtat();
		}
   		
		// Verification couleur piece 1
   		if (statusH) {
   			
   			for(int i = 0; i < 64; i++) {
   				
   				if (g.get(i).getX() == myCase.getX() && g.get(i).getY() == myCase.getY()+1)
   					newCoulH = g.get(i).getPionCase().getCouleur();
   			}
   		}
   		
   		// Verification couleur piece 2
   		if (statusHG) {
   			
   			for(int i = 0; i < 64; i++) {
   				
   				if (g.get(i).getX() == myCase.getX()-1 && g.get(i).getY() == myCase.getY()+1)
   					newCoulHG = g.get(i).getPionCase().getCouleur();
   			}
   		}
   		
   		// Verification couleur piece 1
   		if (statusHD) {
   			
   			for(int i = 0; i < 64; i++) {
   				
   				if (g.get(i).getX() == myCase.getX()+1 && g.get(i).getY() == myCase.getY()+1)
   					newCoulHD = g.get(i).getPionCase().getCouleur();
   			}
   		}
   		
   		// Verification couleur piece 1
   		if (statusB) {
   			
   			for(int i = 0; i < 64; i++) {
   				
   				if (g.get(i).getX() == myCase.getX() && g.get(i).getY() == myCase.getY()-1)
   					newCoulB = g.get(i).getPionCase().getCouleur();
   			}
   		}
   		
   		// Verification couleur piece 1
   		if (statusBG) {
   			
   			for(int i = 0; i < 64; i++) {
   				
   				if (g.get(i).getX() == myCase.getX()-1 && g.get(i).getY() == myCase.getY()-1)
   					newCoulBG = g.get(i).getPionCase().getCouleur();
   			}
   		}
   		
   		// Verification couleur piece 1
   		if (statusBD) {
   			
   			for(int i = 0; i < 64; i++) {
   				
   				if (g.get(i).getX() == myCase.getX()+1 && g.get(i).getY() == myCase.getY()-1)
   					newCoulBD = g.get(i).getPionCase().getCouleur();
   			}
   		}
   		
   		// Verification deplacement
   		
   		// Deplacement vers le haut
   		
   		int testX = myX;
   		int testY = myY+1;
   		
   		if(nX == testX && nY == testY) {
   			
   			if (statusH == true && newCoulH.getId() != myCoul.getId() && verifRoi(returnCaseCoords(nX,nY,myId)) == false)
   				move = true;
   			if (statusH == false)
   				move = true;
   		}
   		
   		// Deplacement vers le haut et la droite
   		
   		testX = myX+1;
   		testY = myY+1;
   		
   		if (nX == testX && nY == testY) {
   			
   			if (statusHD == true && newCoulHD.getId() != myCoul.getId() && verifRoi(returnCaseCoords(nX,nY,myId)) == false)
   				move = true;
   			if (statusHD == false)
   				move = true;
   		}
   		
   		// Deplacement vers le haut et la gauche
   		
   		testX = myX-1;
   		testY = myY+1;
   		
   		if (nX == testX && nY == testY) {

   			if (statusHG == true && newCoulHG.getId() != myCoul.getId() && verifRoi(returnCaseCoords(nX,nY,myId)) == false)
   				move = true;
   			if (statusHG == false)
   				move = true;
   		}
   		
   		// Deplacement vers le bas
   		
   		testX = myX;
   		testY = myY-1;
   		
   		if (nX == testX && nY == testY) {
   			
   			if (statusB == true && newCoulB.getId() != myCoul.getId() && verifRoi(returnCaseCoords(nX,nY,myId)) == false)
   				move = true;
   			if (statusB == false)
   				move = true;
   		}
   		
   		// Deplacement vers le bas et la droite
   		
   		testX = myX+1;
   		testY = myY-1;
   		
   		if (nX == testX && nY == testY) {
   			
   			if (statusBD == true && newCoulBD.getId() != myCoul.getId() && verifRoi(returnCaseCoords(nX,nY,myId)) == false)
   				move = true;
   			if (statusBD == false)
   				move = true;
   		}
   		
   		// Deplacement vers le bas et la gauche
   		
   		testX = myX-1;
   		testY = myY-1;
   		
   		if (nX == testX && nY == testY) {
   			
   			if (statusBG == true && newCoulBG.getId() != myCoul.getId() && verifRoi(returnCaseCoords(nX,nY,myId)) == false)
   				move = true;
   			if (statusBG == false)
   				move = true;
   		}
   		
   		return move;
   	}

   	// Verifier le deplacement du cavalier
  	public boolean checkCavalier(Cases myCase, int nX, int nY, Long myId) {
  		
  		boolean move = false;
  		
  		Couleur newCoulHD1 = null;
  		Couleur newCoulHD2 = null;
  		Couleur newCoulHG1 = null;
  		Couleur newCoulHG2 = null;
  		Couleur newCoulBD1 = null;
  		Couleur newCoulBD2 = null;
  		Couleur newCoulBG1 = null;
  		Couleur newCoulBG2 = null;
  		
   		Couleur myCoul = myCase.getPionCase().getCouleur();
  		
   		int myX = myCase.getX();
  		int myY = myCase.getY();
  		
  		List<Cases> g = games.findById(myId).get().getTable();
   		
   		boolean statusHD1 = false;
   		boolean statusHD2 = false;
   		boolean statusHG1 = false;
   		boolean statusHG2 = false;
   		boolean statusBD1 = false;
   		boolean statusBD2 = false;
   		boolean statusBG1 = false;
   		boolean statusBG2 = false;
   		
   		// Deplacement vers le haut
  		if (myY < nY) {
  			
  			if (nY-myY > 2)
  				return false;
  			
  			// Deplacement vers la droite
  			if (myX < nX && myX < 7) {
  				
  				if (nX-myX > 2)
  					return false;
  				else if (nX-myX == nY-myY)
  					return false;
  				
	 			// Verification presence piece
				Cases caseTemp = returnCaseCoords(myCase.getX()+1,myCase.getY()+2,myId);
				
				statusHD1 = g.get(g.indexOf(caseTemp)).isEtat();
				
				caseTemp = returnCaseCoords(myCase.getX()+2,myCase.getY()+1,myId);
				
				statusHD2 = g.get(g.indexOf(caseTemp)).isEtat();
 				
				// Verification couleur piece 1
				if (statusHD1) {
					
					Cases newTemp = returnCaseCoords(myCase.getX()+1,myCase.getY()+2,myId);
					newCoulHD1 = g.get(g.indexOf(newTemp)).getPionCase().getCouleur();
				}
				
				// Verification couleur piece 1
				if (statusHD2) {
					
					Cases newTemp = returnCaseCoords(myCase.getX()+2,myCase.getY()+1,myId);
					newCoulHD2 = g.get(g.indexOf(newTemp)).getPionCase().getCouleur();
				}
				
				// Verification mouvement
				if (statusHD1 == false && returnCaseCoords(myCase.getX()+1,myCase.getY()+2,myId).getId() == returnCaseCoords(nX,nY,myId).getId()) {
					
					move = true;
				}
				if (statusHD2 == false && returnCaseCoords(myCase.getX()+2,myCase.getY()+1,myId).getId() == returnCaseCoords(nX,nY,myId).getId()) {
					
					move = true;
				}
				if (statusHD1 == true && newCoulHD1.getId() != myCoul.getId() && verifRoi(returnCaseCoords(nX,nY,myId)) == false) {
					
					move = true;
				}
				if (statusHD2 == true && newCoulHD2.getId() != myCoul.getId() && verifRoi(returnCaseCoords(nX,nY,myId)) == false) {
					
					move = true;
				}
  			
  			}
  			// Deplacement vers la gauche
  			else if (myX > nX && myX > 0) {
  				
  				if (myX-nX > 2)
  					return false;
  				else if (myX-nX == nY-myY)
  					return false;
  				
  				// Verification presence piece
  				for(int i = 0; i < 64; i++) {
  					
  					if (g.get(i).getX() == myCase.getX()-1 && g.get(i).getY() == myCase.getY()+2)
  						statusHG1 = g.get(i).isEtat();
  					
  					if (g.get(i).getX() == myCase.getX()-2 && g.get(i).getY() == myCase.getY()+1)
  						statusHG2 = g.get(i).isEtat();
  				}
  				
  				// Verification couleur piece 1
				if (statusHG1) {
					
					for(int i = 0; i < 64; i++) {
						
						if (g.get(i).getX() == myCase.getX()-1 && g.get(i).getY() == myCase.getY()+2)
							newCoulHG1 = g.get(i).getPionCase().getCouleur();
					}
				}
				
				// Verification couleur piece 2
				if (statusHG2) {
					
					for(int i = 0; i < 64; i++) {
						
						if (g.get(i).getX() == myCase.getX()-2 && g.get(i).getY() == myCase.getY()+1)
							newCoulHG2 = g.get(i).getPionCase().getCouleur();
					}
				}
				
				// Verification mouvement
				if (statusHG1 == false && returnCaseCoords(myCase.getX()-1,myCase.getY()+2,myId).getId() == returnCaseCoords(nX,nY,myId).getId()) {
					move = true;
				}
				if (statusHG1 == true && newCoulHG1.getId() != myCoul.getId() && verifRoi(returnCaseCoords(nX,nY,myId)) == false) {
					move = true;
				}
				if (statusHG2 == false && returnCaseCoords(myCase.getX()-2,myCase.getY()+1,myId).getId() == returnCaseCoords(nX,nY,myId).getId()) {
					move = true;
				}
				if (statusHG2 == true && newCoulHG2.getId() != myCoul.getId() && verifRoi(returnCaseCoords(nX,nY,myId)) == false) {
					move = true;
				}
  			}
  		}
  		// Deplacement vers le bas
  		else if (myY > nY) {
  			
  			if (myY-nY > 2)
					return false;
  			
  			// Deplacement vers la droite
  			if (myX < nX && myX < 7) {
  				
  				if (nX-myX > 2)
  					return false;
  				else if (nX-myX == myY-nY)
  					return false;
  				
  				// Verification presence piece
  				for(int i = 0; i < 64; i++) {
  					
  					if (g.get(i).getX() == myCase.getX()+1 && g.get(i).getY() == myCase.getY()-2)
  						statusBD1 = g.get(i).isEtat();
  					
  					if (g.get(i).getX() == myCase.getX()+2 && g.get(i).getY() == myCase.getY()-1)
  						statusBD2 = g.get(i).isEtat();
  				}
  				
  				// Verification couleur piece 1
				if (statusBD1) {
					
					for(int i = 0; i < 64; i++) {
						
						if (g.get(i).getX() == myCase.getX()+1 && g.get(i).getY() == myCase.getY()-2)
							newCoulBD1 = g.get(i).getPionCase().getCouleur();
					}
				}
				
				// Verification couleur piece 2
				if (statusBD2) {
					
					for(int i = 0; i < 64; i++) {
						
						if (g.get(i).getX() == myCase.getX()+2 && g.get(i).getY() == myCase.getY()-1)
							newCoulBD2 = g.get(i).getPionCase().getCouleur();
					}
				}
				
				// Verification mouvement
				if (statusBD1 == false && returnCaseCoords(myCase.getX()+1,myCase.getY()-2,myId).getId() == returnCaseCoords(nX,nY,myId).getId()) {
					move = true;
				}
				if (statusBD1 == true && newCoulBD1.getId() != myCoul.getId() && verifRoi(returnCaseCoords(nX,nY,myId)) == false) {
					move = true;
				}
				if (statusBD2 == false && returnCaseCoords(myCase.getX()+2,myCase.getY()-1,myId).getId() == returnCaseCoords(nX,nY,myId).getId()) {
					move = true;
				}
				if (statusBD2 == true && newCoulBD2.getId()!= myCoul.getId() && verifRoi(returnCaseCoords(nX,nY,myId)) == false) {
					move = true;
				}
  			}
  			// Deplacement vers la gauche
  			else if (myX > nX && myX > 0) {
  				
  				if (myX-nX > 2)
  					return false;
  				else if (myX-nX == myY-nY)
  					return false;
  				
  				// Verification presence piece
  				for(int i = 0; i < 64; i++) {
  					
  					if (g.get(i).getX() == myCase.getX()-1 && g.get(i).getY() == myCase.getY()-2)
  						statusBG1 = g.get(i).isEtat();
  					
  					if (g.get(i).getX() == myCase.getX()-2 && g.get(i).getY() == myCase.getY()-1)
  						statusBG2 = g.get(i).isEtat();
  				}
				
  				// Verification couleur piece 1
				if (statusBG1) {
					
					for(int i = 0; i < 64; i++) {
						
						if (g.get(i).getX() == myCase.getX()-1 && g.get(i).getY() == myCase.getY()-2)
							newCoulBG1 = g.get(i).getPionCase().getCouleur();
					}
				}
		  		
				// Verification couleur piece 2
				if (statusBG2) {
					
					for(int i = 0; i < 64; i++) {
						
						if (g.get(i).getX() == myCase.getX()-2 && g.get(i).getY() == myCase.getY()-1)
							newCoulBG2 = g.get(i).getPionCase().getCouleur();
					}
				}
				
				// Verification mouvement
				if (statusBG1 == false && returnCaseCoords(myCase.getX()-1,myCase.getY()-2,myId).getId() == returnCaseCoords(nX,nY,myId).getId()) {
					move = true;
				}
				if (statusBG1 == true && newCoulBG1.getId() != myCoul.getId() && verifRoi(returnCaseCoords(nX,nY,myId)) == false) {
					move = true;
				}
				if (statusBG2 == false && returnCaseCoords(myCase.getX()-2,myCase.getY()-1,myId).getId() == returnCaseCoords(nX,nY,myId).getId()) {
					move = true;
				}
				if (statusBG2 == true && newCoulBG2.getId() != myCoul.getId() && verifRoi(returnCaseCoords(nX,nY,myId)) == false) {
					move = true;
				}
  			}
  		}
 	
  		return move;
  	}
  	
  	// Verifier le mouvement de chaque piece
  	public boolean checkAllPiece(String name, Cases myCase, int nX, int nY, Long myId) {
  		
  		boolean move = false;
  		
		switch(name) {
		 		
	 		case "Pion":
	 			move = checkPion(myCase,nX,nY,myId);
	 			break;
	 			
	 		case "Tour":
	 			move = checkTour(myCase,nX,nY,myId);
	 			break;
	 			
	 		case "Cavalier":
	 			move = checkCavalier(myCase,nX,nY,myId);
	 			break;
	 			
	 		case "Fou":
	 			move = checkFou(myCase,nX,nY,myId);
	 			break;
	 			
	 		case "Roi":
	 			if (nX-myCase.getX() == 1)
	 				move = checkRoi(myCase,nX,nY,myId);
	 			else if (myCase.getX()-nX == 1)
	 				move = checkRoi(myCase,nX,nY,myId);
	 			else if (nY-myCase.getY() == 1)
	 				move = checkRoi(myCase,nX,nY,myId);
	 			else if (myCase.getY()-nY == 1)
	 				move = checkRoi(myCase,nX,nY,myId);
	 			else
	 				move = checkRoque(myCase,nX,nY,myId);
	 			break;
	 			
	 		case "Reine":
	 			move = checkReine(myCase,nX,nY,myId);
	 			break;
 		}
  		
		return move;
  	}
  	
  	// Retourner une case a partir d'un pion donne
  	public Cases returnCasePion(Pion myP, Long myId) {
  		
  		for(Cases n : games.findById(myId).get().getTable()) {
  			
  			if (n.getPionCase().getId() == myP.getId())
  				return n;
  		}
  		
  		return null;
  	}
  	
  	// Retourner une case a partir d'un pion donne
   	public Cases returnCaseCoords(int caseX, int caseY, Long myId) {
   		
   		for(Cases n : games.findById(myId).get().getTable()) {
   			
   			if (n.getX() == caseX && n.getY() == caseY)
   				return n;
   		}
   		
   		return null;
   	}
  	
  	// Verifier le deplacement et la possible mise en echec du roi
  	public boolean miseEchecRoi(Cases myCase, int nX, int nY, Long myId) {
  		
  		boolean move = false;
  		
  		int posXroi = myCase.getX();
  		int posYroi = myCase.getY();
  		
  		Couleur myCoul = myCase.getPionCase().getCouleur();
  		
  		for(Pion n : Couleurs.findById(otherCoul(myCoul).getId()).get().getPions()) {
  			
  			Cases casePion = returnCasePion(n, myId);
  			
  			move = checkAllPiece(n.getNom(),casePion,posXroi,posYroi,myId);
  			
  			if (move)
  				break;
  		}
  		
  		return move;
  	}
  	
  	// Mise a jour du plateau suite au mouvement
  	public void updatePlateau(Cases myCase, int nX, int nY, Long myId) {
  		
  		Cases newCase = returnCaseCoords(nX,nY,myId);
  		
  		// Mise a jour de la nouvelle case
  		if (myCase.getPionCase().getId() == 6 && nY == 7) {
  			
  			newCase.setPionCase(Pions.findById((long) 5).get());
  			newCase.setEtat(true);
			Case.save(newCase);
  		}
  		else if (myCase.getPionCase().getId() == 12 && nY == 0) {
  			
  			newCase.setPionCase(Pions.findById((long) 11).get());
  			newCase.setEtat(true);
			Case.save(newCase);
  		}
  		else {
  			
			newCase.setPionCase(myCase.getPionCase());
			newCase.setEtat(true);
			Case.save(newCase);
  		}
		
		// Mise a jour de l'ancienne case
		myCase.setEtat(false);
		myCase.setPionCase(null);
  		Case.save(myCase);
  	}
  	
  	// Corriger le plateau si erreur
  	public void correctionPlateau(Cases myCase, int nX, int nY, Pion temp, Long myId) {
  		
		Cases newCase = returnCaseCoords(nX,nY,myId);
  		
		myCase.setEtat(true);
		myCase.setPionCase(newCase.getPionCase());
		Case.save(myCase);
		
		if (temp != null) {
			
			newCase.setPionCase(temp);
			newCase.setEtat(true);
			Case.save(newCase);
		}
		else {
			
			newCase.setPionCase(temp);
			newCase.setEtat(false);
			Case.save(newCase);
		}
  	}
  	
  	// Verifier la couleur du joueur
  	public boolean checkPlayer(Cases myCase, Long myId, String namePlayer) {
  		
  		if (!games.findById(myId).get().getJoueurNoir().getUsername().equals(namePlayer) && myCase.getPionCase().getCouleur().getNom().equals("Blanc"))
  			return true;
  		else if (games.findById(myId).get().getJoueurNoir().getUsername().equals(namePlayer) && myCase.getPionCase().getCouleur().getNom().equals("Noir"))
  			return true;
  		else
  			return false;
  	}
  	
  	// Verifier le mouvement du Roque
   	public boolean checkRoque(Cases myCase, int nX, int nY, Long myId) {
   		
   		boolean move = false;
   		
   		Cases newCase = returnCaseCoords(nX,nY,myId);
   		
   		// Si je clique en second sur la tour blanche
   		if (myCase.getPionCase().getCouleur().getId() == (long) 1) {
   			
   			// Si je clique en second sur la le roi ou la tour
   	   		if (newCase.getPionCase().getId() == 1) {
   	   			
   	   			// Grand Roque droite
   	   			if (myCase.getX() < newCase.getX()) {
   	   				
   	   				if (checkTour(newCase,myCase.getX()+1,myCase.getY(),myId)) {
   	   					
   	   					updateRoque(myCase,nX,nY,myId,true);
   	   					return true;
   	   				}
   	   			}
   	   			// Petit Roque gauche
   	   			else {
   	   				
   	   				if (checkTour(newCase,myCase.getX()-1,myCase.getY(),myId)) {
   	   					
   	   					updateRoque(myCase,nX,nY,myId,false);
   	   					return true;
   	   				}
   	   			}
   	   		}
   		}
   		
   		// Si je clique en second sur la tour noire
   		if (newCase.getPionCase().getId() == 7) {
   			
   			// Grand Roque droite
   			if (myCase.getX() < newCase.getX()) {
   				
   				if (checkTour(newCase,myCase.getX()+1,myCase.getY(),myId)) {
	   					
   					updateRoque(myCase,nX,nY,myId,true);
   					return true;
   				}
   			}
   			// Petit Roque gauche
   			else {
   				
   				if (checkTour(newCase,myCase.getX()-1,myCase.getY(),myId)) {
   					
   					updateRoque(myCase,nX,nY,myId,false);
   					return true;
   				}
   			}
   		}
   		
   		return move;
   	}
  	
   	// Mise a jour du plateau suite au Roque
   	public void updateRoque(Cases myCase, int nX, int nY, Long myId, boolean val) {
   		
   		Cases newCase = returnCaseCoords(nX,nY,myId);
  		
   		if (val) {
   			
   			updatePlateau(myCase,myCase.getX()+2,myCase.getY(),myId);
   			updatePlateau(newCase, newCase.getX()-3,newCase.getY(),myId);
   		}
   		else {
   			
   			updatePlateau(myCase,myCase.getX()-2,myCase.getY(),myId);
   			updatePlateau(newCase, newCase.getX()+2,newCase.getY(),myId);
   		}
   	}
   	
   	// Verifier la victoire
   	public void checkVictoire(Long myId) {
   		
   		Partie p = games.findById(myId).get();
   		Partie temp = null;
   		
   		List<Cases> g = games.findById(myId).get().getTable();
   		
   		int cptN = 0;
   		int cptB = 0;
   		
   		for(int i = 0; i < 64; i++) {
   			
   			if (g.get(i).getPionCase() != null) {
   				
   				if(g.get(i).getPionCase().getCouleur().getId() == (long) 1)
   					cptB++;
   				else
   					cptN++;
   			}
   		}
   		
   		if(cptB == 1 || cptN == 1) {
   			temp = p;
   			temp.setEtat(true);
   			games.save(temp);
   		}
   	}
   	
  	// Verifier le deplacement de la piece
 	public String checkMove(int myX, int myY, int nX, int nY, Long myId, String namePlayer) {
 		
 		boolean move = false;
 		
 		String messageStatus = "ok";
 		
 		Cases myCase = returnCaseCoords(myX,myY,myId);
 		
 		String name = myCase.getPionCase().getNom();
 		
 		Pion tPion = null;
 		
 		if (checkPlayer(myCase,myId,namePlayer))
 			return "Erreur: Interdiction de jouer les pieces adverses";
 		
 		move = checkAllPiece(name,myCase,nX,nY,myId);
 		
 		if (move) {
 			
 			if (returnCaseCoords(nX,nY,myId).isEtat() == true)
 				tPion = returnCaseCoords(nX,nY,myId).getPionCase();
 			
 			updatePlateau(myCase,nX,nY,myId);
 			
 			//move = miseEchecRoi(myCase,nX,nY,myId);
 			
 			/*if (!move) {
	 			correctionPlateau(myCase,nX,nY,tPion,myId);
	 			messageStatus = "Erreur: Roi en echec";
	 		}
	 		else {
	 			messageStatus = "ok";
	 		}*/
 			
 			checkVictoire(myId);
 		}
 		else {
 			
 			messageStatus = "Erreur: Mauvais mouvement de piece";
 		}
 		
 		return messageStatus;
 	}
 	
 	
}
