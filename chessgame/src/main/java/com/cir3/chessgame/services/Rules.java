package com.cir3.chessgame.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cir3.chessgame.domain.Cases;
import com.cir3.chessgame.domain.Couleur;
import com.cir3.chessgame.domain.Partie;
import com.cir3.chessgame.domain.Pion;
import com.cir3.chessgame.repository.CasesRepository;
import com.cir3.chessgame.repository.CouleurRepository;
import com.cir3.chessgame.repository.PartieRepository;

@Service
public class Rules {
	
	protected final PartieRepository games;
	
	protected final CasesRepository Case;
	
	protected final CouleurRepository Couleurs;
	
	@Autowired
	public Rules(PartieRepository games, CasesRepository Case, CouleurRepository Couleurs) {
		this.games = games;
		this.Case = Case;
		this.Couleurs = Couleurs;
	}
	
	// Verifier le deplacement du pion
  	public boolean checkPion(Cases myCase, int nX, int nY) {
  		
  		boolean move = false;
  		
  		Couleur newCoulHG = null;
  		Couleur newCoulHD = null;
  		Couleur myCoul = myCase.getPionCase().getCouleur();
  		
  		int myX = Case.findById(myCase.getId()).get().getX();
  		int myY = Case.findById(myCase.getId()).get().getY();
  		
  		boolean statusH = Case.findById(myCase.getId()+8).get().isEtat();
  		boolean statusHG = Case.findById(myCase.getId()+7).get().isEtat();
  		boolean statusHD = Case.findById(myCase.getId()+9).get().isEtat();
  		
  		if (statusHG == true)
  			newCoulHG = Case.findById(myCase.getId()+7).get().getPionCase().getCouleur();
  		
  		if (statusHD == true)
  			newCoulHD = Case.findById(myCase.getId()+9).get().getPionCase().getCouleur();
  		
  		if(nX == myX && nY == myY++ && statusH == false) {
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
 	public boolean checkTour(Cases myCase, int nX, int nY) {
 		
 		boolean move = false;
 		
 		Couleur newCoul = null;
  		Couleur myCoul = myCase.getPionCase().getCouleur();
 		
 		int myX = Case.findById(myCase.getId()).get().getX();
  		int myY = Case.findById(myCase.getId()).get().getY();
  		
  		boolean status = false;
 		
  		// Deplacement horizontal
 		if (myY == nY && myX != nX) {
 			
 			// Deplacement vers la droite
 			if (myX < nX && myX < 7) {
 				
 				for(int i = myX; i < nX; i++) {
 					
 					status = Case.findById(myCase.getId()+i).get().isEtat();
 					
 					if (status == true)
 			  			newCoul = Case.findById(myCase.getId()+i).get().getPionCase().getCouleur();
 					
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
 					
 					status = Case.findById(myCase.getId()-i).get().isEtat();
 					
 					if (status == true)
 			  			newCoul = Case.findById(myCase.getId()-i).get().getPionCase().getCouleur();
 					
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
 					
 					status = Case.findById(myCase.getId()+(8*i)).get().isEtat();
 					
 					if (status == true)
 			  			newCoul = Case.findById(myCase.getId()+(8*i)).get().getPionCase().getCouleur();
 					
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
 					
 					status = Case.findById(myCase.getId()-(8*i)).get().isEtat();
 					
 					if (status == true)
 			  			newCoul = Case.findById(myCase.getId()-(8*i)).get().getPionCase().getCouleur();
 					
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
 	public boolean checkFou(Cases myCase, int nX, int nY) {
 		
 		boolean move = false;
 		
 		Couleur newCoul = null;
  		Couleur myCoul = myCase.getPionCase().getCouleur();
 		
 		int myX = Case.findById(myCase.getId()).get().getX();
  		int myY = Case.findById(myCase.getId()).get().getY();
  		
  		boolean status = false;
 		
  		// Deplacement vers le haut
 		if (myY < nY) {
 			
 			// Deplacement vers la droite
 			if (myX < nX && myX < 7) {
 				
 				for(int i = myX; i < nX; i++) {
 					
 					status = Case.findById(myCase.getId()+8+i).get().isEtat();
 					
 					if (status == true)
 			  			newCoul = Case.findById(myCase.getId()+8+i).get().getPionCase().getCouleur();
 					
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
 					
 					status = Case.findById(myCase.getId()+8-i).get().isEtat();
 					
 					if (status == true)
 			  			newCoul = Case.findById(myCase.getId()+8-i).get().getPionCase().getCouleur();
 					
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
 		// Deplacement vers le bas
 		else if (myY > nY) {
 			
 			// Deplacement vers la droite
 			if (myX < nX && myX < 7) {
 				
 				for(int i = myX; i < nX; i++) {
 					
 					status = Case.findById(myCase.getId()-8+i).get().isEtat();
 					
 					if (status == true)
 			  			newCoul = Case.findById(myCase.getId()-8+i).get().getPionCase().getCouleur();
 					
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
 					
 					status = Case.findById(myCase.getId()-8-i).get().isEtat();
 					
 					if (status == true)
 			  			newCoul = Case.findById(myCase.getId()-8-i).get().getPionCase().getCouleur();
 					
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
 	 
 	// Verifier le deplacement de la reine
  	public boolean checkReine(Cases myCase, int nX, int nY) {
  		
  		boolean move = false;
  		
  		Couleur newCoul = null;
   		Couleur myCoul = myCase.getPionCase().getCouleur();
  		
  		int myX = Case.findById(myCase.getId()).get().getX();
   		int myY = Case.findById(myCase.getId()).get().getY();
   		
   		boolean status = false;
  		
   		// Deplacement horizontal
  		if (myY == nY && myX != nX) {
  			
  			// Deplacement vers la droite
  			if (myX < nX && myX < 7) {
  				
  				for(int i = myX; i < nX; i++) {
  					
  					status = Case.findById(myCase.getId()+i).get().isEtat();
  					
  					if (status == true)
  			  			newCoul = Case.findById(myCase.getId()+i).get().getPionCase().getCouleur();
  					
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
  					
  					status = Case.findById(myCase.getId()-i).get().isEtat();
  					
  					if (status == true)
  			  			newCoul = Case.findById(myCase.getId()-i).get().getPionCase().getCouleur();
  					
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
  					
  					status = Case.findById(myCase.getId()+(8*i)).get().isEtat();
  					
  					if (status == true)
  			  			newCoul = Case.findById(myCase.getId()+(8*i)).get().getPionCase().getCouleur();
  					
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
 					
 					status = Case.findById(myCase.getId()+8+i).get().isEtat();
 					
 					if (status == true)
 			  			newCoul = Case.findById(myCase.getId()+8+i).get().getPionCase().getCouleur();
 					
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
 					
 					status = Case.findById(myCase.getId()+8-i).get().isEtat();
 					
 					if (status == true)
 			  			newCoul = Case.findById(myCase.getId()+8-i).get().getPionCase().getCouleur();
 					
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
  					
  					status = Case.findById(myCase.getId()-(8*i)).get().isEtat();
  					
  					if (status == true)
  			  			newCoul = Case.findById(myCase.getId()-(8*i)).get().getPionCase().getCouleur();
  					
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
 					
 					status = Case.findById(myCase.getId()-8+i).get().isEtat();
 					
 					if (status == true)
 			  			newCoul = Case.findById(myCase.getId()-8+i).get().getPionCase().getCouleur();
 					
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
 					
 					status = Case.findById(myCase.getId()-8-i).get().isEtat();
 					
 					if (status == true)
 			  			newCoul = Case.findById(myCase.getId()-8-i).get().getPionCase().getCouleur();
 					
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
   	public boolean checkRoi(Cases myCase, int nX, int nY) {
   		
   		boolean move = false;
   		
   		Couleur newCoulH = null;
   		Couleur newCoulHG = null;
   		Couleur newCoulHD = null;
   		
   		Couleur newCoulB = null;
   		Couleur newCoulBG = null;
   		Couleur newCoulBD = null;
   		
   		Couleur myCoul = myCase.getPionCase().getCouleur();
   		
   		int myX = Case.findById(myCase.getId()).get().getX();
   		int myY = Case.findById(myCase.getId()).get().getY();
   		
   		boolean statusH = Case.findById(myCase.getId()+8).get().isEtat();
   		boolean statusHG = Case.findById(myCase.getId()+7).get().isEtat();
   		boolean statusHD = Case.findById(myCase.getId()+9).get().isEtat();
   		
   		boolean statusB = Case.findById(myCase.getId()-8).get().isEtat();
   		boolean statusBG = Case.findById(myCase.getId()-9).get().isEtat();
   		boolean statusBD = Case.findById(myCase.getId()-7).get().isEtat();
   		
   		if (statusH == true)
   			newCoulH = Case.findById(myCase.getId()+8).get().getPionCase().getCouleur();
   		
   		if (statusHG == true)
   			newCoulHG = Case.findById(myCase.getId()+7).get().getPionCase().getCouleur();
   		
   		if (statusHD == true)
   			newCoulHD = Case.findById(myCase.getId()+9).get().getPionCase().getCouleur();
   		
   		if (statusB == true)
   			newCoulB = Case.findById(myCase.getId()-8).get().getPionCase().getCouleur();
   		
   		if (statusBG == true)
   			newCoulBG = Case.findById(myCase.getId()-9).get().getPionCase().getCouleur();
   		
   		if (statusBD == true)
   			newCoulBD = Case.findById(myCase.getId()-7).get().getPionCase().getCouleur();
   		
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
  	public boolean checkCavalier(Cases myCase, int nX, int nY) {
  		
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
  		
  		int myX = Case.findById(myCase.getId()).get().getX();
   		int myY = Case.findById(myCase.getId()).get().getY();
   		
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
  				
				statusHD1 = Case.findById(myCase.getId()+8+8+1).get().isEtat();
				statusHD2 = Case.findById(myCase.getId()+8+2).get().isEtat();
				
				if (statusHD1 == true)
		  			newCoulHD1 = Case.findById(myCase.getId()+8+8+1).get().getPionCase().getCouleur();
				
				if (statusHD2 == true)
		  			newCoulHD2 = Case.findById(myCase.getId()+8+2).get().getPionCase().getCouleur();
				
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
  				
  				statusHG1 = Case.findById(myCase.getId()+8+8-1).get().isEtat();
				statusHG2 = Case.findById(myCase.getId()+8-2).get().isEtat();
				
				if (statusHG1 == true)
		  			newCoulHG1 = Case.findById(myCase.getId()+8+8-1).get().getPionCase().getCouleur();
				
				if (statusHG2 == true)
		  			newCoulHG2 = Case.findById(myCase.getId()+8-2).get().getPionCase().getCouleur();
				
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
  				
  				statusBD1 = Case.findById(myCase.getId()-8-8+1).get().isEtat();
				statusBD2 = Case.findById(myCase.getId()-8+2).get().isEtat();
				
				if (statusBD1 == true)
		  			newCoulBD1 = Case.findById(myCase.getId()-8+8-1).get().getPionCase().getCouleur();
				
				if (statusBD2 == true)
		  			newCoulBD2 = Case.findById(myCase.getId()-8+2).get().getPionCase().getCouleur();
				
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
  				
  				statusBG1 = Case.findById(myCase.getId()-8-8-1).get().isEtat();
				statusBG2 = Case.findById(myCase.getId()-8-2).get().isEtat();
				
				if (statusBG1 == true)
		  			newCoulBG1 = Case.findById(myCase.getId()-8-8-1).get().getPionCase().getCouleur();
				
				if (statusBG2 == true)
		  			newCoulBG2 = Case.findById(myCase.getId()-8-2).get().getPionCase().getCouleur();
				
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

  	// Verifier le mouvement de chaque piece
  	public boolean checkAllPiece(String name, Cases myCase, int nX, int nY) {
  		
  		boolean move = false;
  		
		switch(name) {
		 		
	 		case "Pion":
	 			move = checkPion(myCase,nX,nY);
	 			break;
	 			
	 		case "Tour":
	 			move = checkTour(myCase,nX,nY);
	 			break;
	 			
	 		case "Cavalier":
	 			move = checkCavalier(myCase,nX,nY);
	 			break;
	 			
	 		case "Fou":
	 			move = checkFou(myCase,nX,nY);
	 			break;
	 			
	 		case "Roi":
	 			move = checkRoi(myCase,nX,nY);
	 			break;
	 			
	 		case "Reine":
	 			move = checkReine(myCase,nX,nY);
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
  		
  		int posXroi = Case.findById(myCase.getId()).get().getX();
  		int posYroi = Case.findById(myCase.getId()).get().getY();
  		
  		Couleur myCoul = myCase.getPionCase().getCouleur();
  		
  		for(Pion n : Couleurs.findById(myCoul.returnOtherCoul(myCoul).getId()).get().getPions()) {
  			
  			Cases casePion = returnCasePion(n, myId);
  			
  			move = checkAllPiece(n.getNom(),casePion,posXroi,posYroi);
  			
  			if (move)
  				break;
  		}
  		
  		return move;
  	}
  	
  	// Mise a jour du plateau suite au mouvement
  	public void updatePlateau(Cases myCase, int nX, int nY, Long myId) {
  		
  		Cases newCase = returnCaseCoords(nX,nY,myId);
  		
  		for(Cases n : games.findById(myId).get().getTable()) {
  			
  			if (n.getId() == newCase.getId())
  				n = newCase;
  		}
  		
  		Partie temp = games.findById(myId).get();
  		
  		games.save(temp);
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
  	
  	// Verifier le deplacement de la piece
 	public String checkMove(int myX, int myY, int nX, int nY, Long myId, String namePlayer) {
 		
 		boolean move = false;
 		
 		String messageStatus = "";
 		
 		Cases myCase = returnCaseCoords(myX,myY,myId);
 		
 		String name = myCase.getPionCase().getNom();
 		
 		Pion tPion = null;
 		
 		if (checkPlayer(myCase,myId,namePlayer))
 			return "Erreur: Interdiction de jouer les pieces adverses";
 		
 		move = checkAllPiece(name,myCase,nX,nY);
 		
 		if (move) {
 			
 			if (returnCaseCoords(nX,nY,myId).isEtat() == true)
 				tPion = returnCaseCoords(nX,nY,myId).getPionCase();
 			
 			updatePlateau(myCase,nX,nY,myId);
 			
 			move = miseEchecRoi(myCase,nX,nY,myId);
 			
 		}
 		else {
 			messageStatus = "Erreur: Mauvais mouuvement de piece";
 		}
 		
 		if (!move) {
 			correctionPlateau(myCase,nX,nY,tPion,myId);
 			messageStatus = "Erreur: Roi en echec";
 		}
 		else {
 			messageStatus = "ok";
 		}
 		
 		return messageStatus;
 	}
}
