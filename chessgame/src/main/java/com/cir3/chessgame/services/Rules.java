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
	
	public Couleur otherCoul(Couleur myCoul) {
	    
    	if (myCoul.getNom().equals("Noir"))
    		return Couleurs.findById((long) 1).get();
    	else
    		return Couleurs.findById((long) 2).get();
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
				g.get(i).setPionCase(Pions.findById((long) 5).get());
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
  		
  		Couleur newCoulHG = null;
  		Couleur newCoulHD = null;
  		Couleur myCoul = myCase.getPionCase().getCouleur();
  		
  		int myX = myCase.getX();
  		int myY = myCase.getY();
  		
  		List<Cases> g = games.findById(myId).get().getTable();
  		
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
  		
  		if(nX == myX && nY == myY++ && statusH == false) {
  			move = true;
  		}
  		else if (nX == myX && nY == myY+2 && statusHH == false) {
  			move = true;
  		}
  		else if (nX == myX++ && nY == myY++ && statusHD == true && !newCoulHD.getNom().equals(myCoul.getNom())) {
  			move = true;
  		}
  		else if (nX == myX-- && nY == myY++ && statusHG == true && !newCoulHG.getNom().equals(myCoul.getNom())) {
  			move = true;
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
 				
 				for(int i = myX; i < nX; i++) {
 					
 					// Verification presence piece
 					for(int j = 0; j < 64; j++) {
 						
 						if (g.get(j).getX() == i && g.get(j).getY() == myCase.getY())
 							status = g.get(j).isEtat();
 						
 						if (status == true && i != nX-1)
 							break;
 					}
 					
 					// Verification couleur piece
 					if (status) {
 						
 						for(int j = 0; j < 64; j++) {
 							
 							if (g.get(j).getX() == i && g.get(j).getY() == myCase.getY())
 								newCoul = g.get(j).getPionCase().getCouleur();
 						}
 					}
 					
 					if (status == true && i != nX-1) {
 						
 						move = false;
 						break;
 					}
 					else if (status == false && i == nX-1) {
 						
 						move = true;
 						break;
 					}
 					else if (status == true && i == nX-1 && !newCoul.getNom().equals(myCoul.getNom())) {
 						
 						move = true;
 						break;
 					}
 				}
 			}
 			// Deplacement vers la gauche
 			else if (myX > nX && myX > 0) {
 				
 				for(int i = myX; i > nX; i--) {
 					
 					// Verification presence piece
 					for(int j = 0; j < 64; j++) {
 						
 						if (g.get(j).getX() == i && g.get(j).getY() == myCase.getY())
 							status = g.get(j).isEtat();
 						
 						if (status == true && i != nX+1)
 							break;
 					}
 					
 					// Verification couleur piece
 					if (status) {
 						
 						for(int j = 0; j < 64; j++) {
 							
 							if (g.get(j).getX() == i && g.get(j).getY() == myCase.getY())
 								newCoul = g.get(j).getPionCase().getCouleur();
 						}
 					}
 			  		
 					if (status == true && i != nX+1) {
 						
 						move = false;
 						break;
 					}
 					else if (status == false && i == nX+1) {
 						
 						move = true;
 						break;
 					}
 					else if (status == true && i == nX+1 && !newCoul.getNom().equals(myCoul.getNom())) {
 						
 						move = true;
 						break;
 					}
 				}
 			}
 		}
 		// Deplacement vertical
 		else if (myY != nY && myX == nX) {
 			
 			// Deplacement vers le haut
 			if (myY < nY && myY < 7) {
 				
 				for(int i = myY; i < nY; i++) {
 					
 					// Verification presence piece
 					for(int j = 0; j < 64; j++) {
 						
 						if (g.get(j).getX() == myCase.getX() && g.get(j).getY() == i)
 							status = g.get(j).isEtat();
 						
 						if (status == true && i != nY-1)
 							break;
 					}
 					
 					// Verification couleur piece
 					if (status) {
 						
 						for(int j = 0; j < 64; j++) {
 							
 							if (g.get(j).getX() == myCase.getX() && g.get(j).getY() == i)
 								newCoul = g.get(j).getPionCase().getCouleur();
 						}
 					}
 			  		
 					if (status == true && i != nY-1) {
 						
 						move = false;
 						break;
 					}
 					else if (status == false && i == nY-1) {
 						
 						move = true;
 						break;
 					}
 					else if (status == true && i == nY-1 && !newCoul.getNom().equals(myCoul.getNom())) {
 						
 						move = true;
 						break;
 					}
 				}
 			}
 			// Deplacement vers le bas
 			else if (myY > nY && myY > 0) {
 				
 				for(int i = myY; i > nY; i--) {
 					
 					// Verification presence piece
 					for(int j = 0; j < 64; j++) {
 						
 						if (g.get(j).getX() == myCase.getX() && g.get(j).getY() == i)
 							status = g.get(j).isEtat();
 						
 						if (status == true && i != nY+1)
 							break;
 					}
 					
 					// Verification couleur piece
 					if (status) {
 						
 						for(int j = 0; j < 64; j++) {
 							
 							if (g.get(j).getX() == myCase.getX() && g.get(j).getY() == i)
 								newCoul = g.get(j).getPionCase().getCouleur();
 						}
 					}
 					
 					if (status == true && i != nY+1) {
 						
 						move = false;
 						break;
 					}
 					else if (status == false && i == nY+1) {
 						
 						move = true;
 						break;
 					}
 					else if (status == true && i == nY+1 && !newCoul.getNom().equals(myCoul.getNom())) {
 						
 						move = true;
 						break;
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
 				
 				for(int i = myY; i < nY; i++) {
 					
 					for(int k = myX; k < nX; k++) {
 						
 						// Verification presence piece
 	 					for(int j = 0; j < 64; j++) {
 	 						
 	 						if (g.get(j).getX() == k && g.get(j).getY() == i)
 	 							status = g.get(j).isEtat();
 	 						
 	 						if (status == true && k != nX-1 && i != nY-1)
 	 							break;
 	 					}
 	 					
 	 					// Verification couleur piece
 	 					if (status) {
 	 						
 	 						for(int j = 0; j < 64; j++) {
 	 							
 	 							if (g.get(j).getX() == k && g.get(j).getY() == i)
 	 								newCoul = g.get(i).getPionCase().getCouleur();
 	 						}
 	 					}
 	 					
 	 					// Verification mouvement
 	 					if (status == true && k != nX-1 && i != nY-1) {
 	 						
 	 						move = false;
 	 						break;
 	 					}
 	 					else if (status == false && i == nY-1 && k == nX-1) {
 	 						
 	 						move = true;
 	 						break;
 	 					}
 	 					else if (status == true && i == nY-1 && k == nX-1 && !newCoul.getNom().equals(myCoul.getNom())) {
 	 						
 	 						move = true;
 	 						break;
 	 					}
 					}
 				}
 			}
 			// Deplacement vers la gauche
 			else if (myX > nX && myX > 0) {
 				
 				for(int i = myY; i < nY; i++) {
 					
 					for(int k = myX; k > nX; k--) {
 						
 						// Verification presence piece
 	 					for(int j = 0; j < 64; j++) {
 	 						
 	 						if (g.get(j).getX() == k && g.get(j).getY() == i)
 	 							status = g.get(j).isEtat();
 	 						
 	 						if (status == true && k != nX+1 && i != nY-1)
 	 							break;
 	 					}
 	 					
 	 					// Verification couleur piece
 	 					if (status) {
 	 						
 	 						for(int j = 0; j < 64; j++) {
 	 							
 	 							if (g.get(j).getX() == k && g.get(j).getY() == i)
 	 								newCoul = g.get(j).getPionCase().getCouleur();
 	 						}
 	 					}
 	 			  		
 	 					// Verification mouvement
 	 					if (status == true && k != nX+1 && i != nY-1) {
 	 						
 	 						move = false;
 	 						break;
 	 					}
 	 					else if (status == false && i == nY-1 && k == nX+1) {
 	 						
 	 						move = true;
 	 						break;
 	 					}
 	 					else if (status == true && i == nY-1 && k == nX+1 && !newCoul.getNom().equals(myCoul.getNom())) {
 	 						
 	 						move = true;
 	 						break;
 	 					}
 					}
 				}
 			}
 		}
 		// Deplacement vers le bas
 		else if (myY > nY) {
 			
 			// Deplacement vers la droite
 			if (myX < nX && myX < 7) {
 				
 				for(int i = myY; i > nY; i--) {
 					
 					for(int k = myX; k < nX; k++) {
 						
 						// Verification presence piece
 	 					for(int j = 0; j < 64; j++) {
 	 						
 	 						if (g.get(j).getX() == k && g.get(j).getY() == i)
 	 							status = g.get(j).isEtat();
 	 						
 	 						if (status == true && k != nX-1 && i != nY+1)
 	 							break;
 	 					}
 	 					
 	 					// Verification couleur piece
 	 					if (status) {
 	 						
 	 						for(int j = 0; j < 64; j++) {
 	 							
 	 							if (g.get(j).getX() == k && g.get(j).getY() == i)
 	 								newCoul = g.get(j).getPionCase().getCouleur();
 	 						}
 	 					}
 	 					
 	 					// Verification mouvement
 	 					if (status == true && k != nX-1 && i != nY+1) {
 	 						
 	 						move = false;
 	 						break;
 	 					}
 	 					else if (status == false && i == nY+1 && k == nX-1) {
 	 						
 	 						move = true;
 	 						break;
 	 					}
 	 					else if (status == true && i == nY+1 && k == nX-1 && !newCoul.getNom().equals(myCoul.getNom())) {
 	 						
 	 						move = true;
 	 						break;
 	 					}
 					}
 				}
 			}
 			// Deplacement vers la gauche
 			else if (myX > nX && myX > 0) {
 				
 				for(int i = myY; i > nY; i--) {
 					
 					for(int k = myX; k > nX; k--) {
 						
 						// Verification presence piece
 	 					for(int j = 0; j < 64; j++) {
 	 						
 	 						
 	 							status = g.get(j).isEtat();
 	 						
 	 						if (status == true && k != nX+1 && i != nY+1)
 	 							break;
 	 					}
 						
 	 					// Verification couleur piece
 	 					if (status) {
 	 						
 	 						for(int j = 0; j < 64; j++) {
 	 							
 	 							if (g.get(j).getX() == k && g.get(j).getY() == i)
 	 								newCoul = g.get(i).getPionCase().getCouleur();
 	 						}
 	 					}
 	 					
 	 					// Verification mouvement
 	 					if (status == true && k != nX+1 && i != nY+1) {
 	 						
 	 						move = false;
 	 						break;
 	 					}
 	 					else if (status == false && i == nY+1 && k == nX+1) {
 	 						
 	 						move = true;
 	 						break;
 	 					}
 	 					else if (status == true && i == nY+1 && k == nX+1 && !newCoul.getNom().equals(myCoul.getNom())) {
 	 						
 	 						move = true;
 	 						break;
 	 					}
 					}
 				}
 			}
 		}
	
 		return move;
 	}
 	 
 	// Verifier le deplacement de la reine
  	public boolean checkReine(Cases myCase, int nX, int nY, Long myId) {
  		
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
  				
  				for(int i = myX; i < nX; i++) {
  					
  					status = g.get((int) (myCase.getId()+i)).isEtat();
  					
  					if (status == true)
  			  			newCoul = g.get((int) (myCase.getId()+i)).getPionCase().getCouleur();
  					
  					if (status == true && i != nX-1) {
  						
  						move = false;
  						break;
  						
  					}
  					else if (status == false && i == nX-1) {
  						
  						move = true;
  						break;
  						
  					}
  					else if (status == true && i == nX-1 && !newCoul.getNom().equals(myCoul.getNom())) {
  						
  						move = true;
  						break;
  						
  					}
  				}
  			}
  			// Deplacement vers la gauche
  			else if (myX > nX && myX > 0) {
  				
  				for(int i = myX; i > nX; i--) {
  					
  					status = g.get((int) (myCase.getId()-i)).isEtat();
  					
  					if (status == true)
  			  			newCoul = g.get((int) (myCase.getId()-i)).getPionCase().getCouleur();
  					
  					if (status == true && i != nX+1) {
  						
  						move = false;
  						break;
  						
  					}
  					else if (status == false && i == nX+1) {
  						
  						move = true;
  						break;
  						
  					}
  					else if (status == true && i == nX+1 && !newCoul.getNom().equals(myCoul.getNom())) {
  						
  						move = true;
  						break;
  						
  					}
  				}
  			}
  		}
  		// Deplacement vertical
  		else if (myY != nY) {
  			
  			// Deplacement vers le haut
  			if (myY < nY && myY < 7 && myX == nX) {
  				
  				for(int i = myY; i < nY; i++) {
  					
  					status = g.get((int) (myCase.getId()+(8*i))).isEtat();
  					
  					if (status == true)
  			  			newCoul = g.get((int) (myCase.getId()+(8*i))).getPionCase().getCouleur();
  					
  					if (status == true && i != nY-1) {
  						
  						move = false;
  						break;
  						
  					}
  					else if (status == false && i == nY-1) {
  						
  						move = true;
  						break;
  						
  					}
  					else if (status == true && i == nY-1 && !newCoul.getNom().equals(myCoul.getNom())) {
  						
  						move = true;
  						break;
  						
  					}
  					
  				}
  			}
  			// Deplacement vers le haut et la droite
  			else if (myY < nY && myY < 7 && myX < nX && myX < 7) {
 				
 				for(int i = myX; i < nX; i++) {
 					
 					status = g.get((int) (myCase.getId()+8+i)).isEtat();
 					
 					if (status == true)
 			  			newCoul = g.get((int) (myCase.getId()+8+i)).getPionCase().getCouleur();
 					
 					if (status == true && i != nX-1) {
 						
 						move = false;
 						break;
 						
 					}
 					else if (status == false && i == nX-1) {
 						
 						move = true;
 						break;
 						
 					}
 					else if (status == true && i == nX-1 && !newCoul.getNom().equals(myCoul.getNom())) {
 						
 						move = true;
 						break;
 						
 					}
 				}
 			}
 			// Deplacement vers le haut et la gauche
 			else if (myY < nY && myY < 7 && myX > nX && myX > 0) {
 				
 				for(int i = myX; i > nX; i--) {
 					
 					status = g.get((int) (myCase.getId()+8-i)).isEtat();
 					
 					if (status == true)
 			  			newCoul = g.get((int) (myCase.getId()+8-i)).getPionCase().getCouleur();
 					
 					if (status == true && i != nX+1) {
 						
 						move = false;
 						break;
 						
 					}
 					else if (status == false && i == nX+1) {
 						
 						move = true;
 						break;
 						
 					}
 					else if (status == true && i == nX+1 && !newCoul.getNom().equals(myCoul.getNom())) {
 						
 						move = true;
 						break;
 						
 					}
 				}
 			}
  			// Deplacement vers le bas
  			else if (myY > nY && myY > 0 && myX == nX) {
  				
  				for(int i = myY; i > nY; i--) {
  					
  					status = g.get((int) (myCase.getId()-(8*i))).isEtat();
  					
  					if (status == true)
  			  			newCoul = g.get((int) (myCase.getId()-(8*i))).getPionCase().getCouleur();
  					
  					if (status == true && i != nY+1) {
  						
  						move = false;
  						break;
  						
  					}
  					else if (status == false && i == nY+1) {
  						
  						move = true;
  						break;
  						
  					}
  					else if (status == true && i == nY+1 && !newCoul.getNom().equals(myCoul.getNom())) {
  						
  						move = true;
  						break;
  						
  					}
  				}
  			}
  			// Deplacement vers la droite
  			else if (myY > nY && myY > 0 && myX < nX && myX < 7) {
 				
 				for(int i = myX; i < nX; i++) {
 					
 					status = g.get((int) (myCase.getId()-8+i)).isEtat();
 					
 					if (status == true)
 			  			newCoul = g.get((int) (myCase.getId()-8+i)).getPionCase().getCouleur();
 					
 					if (status == true && i != nX-1) {
 						
 						move = false;
 						break;
 						
 					}
 					else if (status == false && i == nX-1) {
 						
 						move = true;
 						break;
 						
 					}
 					else if (status == true && i == nX-1 && !newCoul.getNom().equals(myCoul.getNom())) {
 						
 						move = true;
 						break;
 						
 					}
 				}
 			}
 			// Deplacement vers la gauche
 			else if (myY > nY && myY > 0 && myX > nX && myX > 0) {
 				
 				for(int i = myX; i > nX; i--) {
 					
 					status = g.get((int) (myCase.getId()-8-i)).isEtat();
 					
 					if (status == true)
 			  			newCoul = g.get((int) (myCase.getId()-8-i)).getPionCase().getCouleur();
 					
 					if (status == true && i != nX+1) {
 						
 						move = false;
 						break;
 						
 					}
 					else if (status == false && i == nX+1) {
 						
 						move = true;
 						break;
 						
 					}
 					else if (status == true && i == nX+1 && !newCoul.getNom().equals(myCoul.getNom())) {
 						
 						move = true;
 						break;
 						
 					}
 				}
 			}
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
   		
   		// Deplacement vers le haut
   		if(nX == myX && nY == myY++) {
   			
   			if (statusH == true && !newCoulH.getNom().equals(myCoul.getNom()))
   				move = true;
   			else if (statusH == false)
   				move = true;
   		}
   		// Deplacement vers le haut et la droite
   		else if (nX == myX++ && nY == myY++) {
   			
   			if (statusHD == true && !newCoulHD.getNom().equals(myCoul.getNom()))
   				move = true;
   			else if (statusHD == false)
   				move = true;
   		}
   		// Deplacement vers le haut et la gauche
   		else if (nX == myX-- && nY == myY++) {

   			if (statusHG == true && !newCoulHG.getNom().equals(myCoul.getNom()))
   				move = true;
   			else if (statusHG == false)
   				move = true;
   		}
   		// Deplacement vers le bas
   		else if (nX == myX && nY == myY--) {
   			
   			if (statusB == true && !newCoulB.getNom().equals(myCoul.getNom()))
   				move = true;
   			else if (statusB == false)
   				move = true;
   		}
   		// Deplacement vers le bas et la droite
   		else if (nX == myX++ && nY == myY--) {
   			
   			if (statusBD == true && !newCoulBD.getNom().equals(myCoul.getNom()))
   				move = true;
   			else if (statusBD == false)
   				move = true;
   		}
   		// Deplacement vers le bas et la gauche
   		else if (nX == myX-- && nY == myY--) {
   			
   			if (statusBG == true && !newCoulBG.getNom().equals(myCoul.getNom()))
   				move = true;
   			else if (statusBG == false)
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
  			
  			// Deplacement vers la droite
  			if (myX < nX && myX < 7) {
  				
  				// Verification presence piece
  				for(int i = 0; i < 64; i++) {
  					
  					if (g.get(i).getX() == myCase.getX()+1 && g.get(i).getY() == myCase.getY()+2)
  						statusHD1 = g.get(i).isEtat();
  					
  					if (g.get(i).getX() == myCase.getX()+2 && g.get(i).getY() == myCase.getY()+1)
  						statusHD2 = g.get(i).isEtat();
  				}
				
  				// Verification couleur piece 1
				if (statusHD1) {
					
					for(int i = 0; i < 64; i++) {
						
						if (g.get(i).getX() == myCase.getX()+1 && g.get(i).getY() == myCase.getY()+2)
							newCoulHD1 = g.get(i).getPionCase().getCouleur();
					}
				}
				
				// Verification couleur piece 2
				if (statusHD2) {
					
					for(int i = 0; i < 64; i++) {
						
						if (g.get(i).getX() == myCase.getX()+2 && g.get(i).getY() == myCase.getY()+1)
							newCoulHD2 = g.get(i).getPionCase().getCouleur();
					}
				}
				
				// Verification mouvement
				if (statusHD1 == false) {
					move = true;
				}
				else if (statusHD1 == true && !newCoulHD1.getNom().equals(myCoul.getNom())) {
					move = true;
				}
				else if (statusHD2 == false) {
					move = true;
				}
				else if (statusHD2 == true && !newCoulHD2.getNom().equals(myCoul.getNom())) {
					move = true;
				}
  			
  			}
  			// Deplacement vers la gauche
  			else if (myX > nX && myX > 0) {
  				
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
				if (statusHG1 == false) {
					move = true;
				}
				else if (statusHG1 == true && !newCoulHG1.getNom().equals(myCoul.getNom())) {
					move = true;
				}
				else if (statusHG2 == false) {
					move = true;
				}
				else if (statusHG2 == true && !newCoulHG2.getNom().equals(myCoul.getNom())) {
					move = true;
				}
  			}
  		}
  		// Deplacement vers le bas
  		else if (myY > nY) {
  			
  			// Deplacement vers la droite
  			if (myX < nX && myX < 7) {
  				
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
				if (statusBD1 == false) {
					move = true;
				}
				else if (statusBD1 == true && !newCoulBD1.getNom().equals(myCoul.getNom())) {
					move = true;
				}
				else if (statusBD2 == false) {
					move = true;
				}
				else if (statusBD2 == true && !newCoulBD2.getNom().equals(myCoul.getNom())) {
					move = true;
				}
  			}
  			// Deplacement vers la gauche
  			else if (myX > nX && myX > 0) {
  				
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
				if (statusBG1 == false) {
					move = true;
				}
				else if (statusBG1 == true && !newCoulBG1.getNom().equals(myCoul.getNom())) {
					move = true;
				}
				else if (statusBG2 == false) {
					move = true;
				}
				else if (statusBG2 == true && !newCoulBG2.getNom().equals(myCoul.getNom())) {
					move = true;
				}
  			}
  		}
 	
  		return move;
  	}
  	
  	// Verification par toutes les pieces

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
	 			move = checkRoi(myCase,nX,nY,myId);
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
  		
  		System.out.println("!!!!!!!!! BOUGE TOI !!!!!!!!!!");
  		
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
		newCase.setPionCase(myCase.getPionCase());
		newCase.setEtat(true);
		Case.save(newCase);
		
		// Mise a jour de l'ancienne case
		myCase.setEtat(false);
		myCase.setPionCase(null);
  		Case.save(myCase);
  	}
  	
  	// Corriger le plateau si erreur
  	public void correctionPlateau(Cases myCase, int nX, int nY, Pion temp, Long myId) {
  		
  		if (temp != null) {
  			
  			Cases newCase = returnCaseCoords(nX,nY,myId);
  	  		
  	  		for(Cases n : games.findById(myId).get().getTable()) {
  	  			
  	  			if (n.getId() == myCase.getId()) {
	  				n.setPionCase(returnCaseCoords(nX,nY,myId).getPionCase());
	  				break;
	  			}
  	  		}
  	  		
  	  		for(Cases n : games.findById(myId).get().getTable()) {
	  	  		
  	  			if (n.getId() == newCase.getId()) {
					n.setPionCase(temp);
					break;
  	  			}
  	  		}
  		}
  		else {
  			
  			for(Cases n : games.findById(myId).get().getTable()) {
  	  			
  	  			if (n.getId() == myCase.getId()) {
	  				n.setPionCase(returnCaseCoords(nX,nY,myId).getPionCase());
	  				break;
  	  			}
  	  		}
  		}
  		
  		Partie tPart = games.findById(myId).get();
  		
  		games.save(tPart);
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
   		boolean status = false;
   		
   		List<Cases> g = games.findById(myId).get().getTable();
   		
   		Cases newCase = returnCaseCoords(nX,nY,myId);
   		
   		// Si je clique en second sur la le roi ou la tour
   		if (newCase.getPionCase().getNom().equals("Roi") || newCase.getPionCase().getNom().equals("Tour")) {
   			
   			// Grand Roque droite
   			if (myCase.getX() < newCase.getX()) {
   				
   				for(int i = 0; i < 3; i++) {
   					
   					status = g.get((int) (myCase.getId()+i)).isEtat();
   					
   					if (status)
   						return false;
   				}
   			}
   			// Petit Roque gauche
   			else {
   				
   				for(int i = 0; i < 2; i++) {
   					
   					status = g.get((int) (myCase.getId()-i)).isEtat();
   					
   					if (status)
   						return false;
   				}
   			}
   		}
   		
   		return move;
   	}
  	
   	// Mise a jour du plateau suite au Roque
   	/*public void updateRoque(Cases myCase, int nX, int nY, Long myId) {
   		
   		Cases newCase = returnCaseCoords(nX,nY,myId);
  		
   		// Nouvelle case Tour
   		if (newCase.getPionCase().getNom().equals("Tour")) {
   			
   			// Grand Roque droite
   			if (myCase.getX() < newCase.getX()) {
   	   			
   				for(Cases n : games.findById(myId).get().getTable()) {
   		  			
   		  			if (n.getX())
   		  				n = newCase;
   		  		}
   	   			
   	   		}
   			// Petit Roque gauche
   	   		else {
   	   			
   	   			
   	   			
   	   		}
		}
   		// Nouvelle case Roi
		else {
			
			// Petit Roque gauche
   			if (myCase.getX() < newCase.getX()) {
   	   			
   	   			
   	   			
   	   		}
   			// Grand Roque droite
   	   		else {
   	   			
   	   			
   	   			
   	   		}
		}
   		
  		for(Cases n : games.findById(myId).get().getTable()) {
  			
  			if (n.getId() == newCase.getId())
  				n = newCase;
  		}
  		
  		Partie temp = games.findById(myId).get();
  		
  		games.save(temp);
   		
   	}*/
   	
   	// Promotion du pion
   	public void promotePion(Long myId) {
   		
   		List<Cases> g = games.findById(myId).get().getTable();
   		
   		for(int i = 0; i < 64; i++) {
   			
   			if (g.get(i).getY() == 0 && g.get(i).getPionCase().getCouleur().getNom().equals("Noir") && g.get(i).getPionCase().getNom().equals("Pion")) {
   				
   				g.get(i).setPionCase(Pions.findById((long) 5).get());
   				Case.save(g.get(i));
   			}
   			else if (g.get(i).getY() == 7 && g.get(i).getPionCase().getCouleur().getNom().equals("Blanc") && g.get(i).getPionCase().getNom().equals("Pion")) {
   				
   				g.get(i).setPionCase(Pions.findById((long) 11).get());
   				Case.save(g.get(i));
   			}
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
 			
 			promotePion(myId);
 			
 			//move = miseEchecRoi(myCase,nX,nY,myId);
 			
 		}
 		else {
 			messageStatus = "Erreur: Mauvais mouvement de piece";
 		}
 		
 		/*if (!move) {
 			correctionPlateau(myCase,nX,nY,tPion,myId);
 			messageStatus = "Erreur: Roi en echec";
 		}
 		else {
 			messageStatus = "ok";
 		}*/
 		
 		return messageStatus;
 	}
 	
 	
}
