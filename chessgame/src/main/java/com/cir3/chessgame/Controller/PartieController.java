package com.cir3.chessgame.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cir3.chessgame.domain.Cases;
import com.cir3.chessgame.domain.Couleur;
import com.cir3.chessgame.domain.Pion;
import com.cir3.chessgame.repository.CasesRepository;
import com.cir3.chessgame.repository.PartieRepository;
import com.cir3.chessgame.repository.PionRepository;

@Controller
@RequestMapping("/partie")
public class PartieController {
	
	private PartieRepository games;
	
	private CasesRepository Case;
	
	private PionRepository Pions;
	
	@GetMapping("")
	public String partie() {
		
		
	  	return "partie";
	}
	
	// Verifier le deplacement du pion
  	public boolean checkPion(Cases myCase, int nX, int nY) {
  		
  		boolean move = false;
  		
  		Couleur newCoulH = null;
  		Couleur newCoulHG = null;
  		Couleur newCoulHD = null;
  		Couleur myCoul = myCase.getPionCase().getCouleur();
  		
  		int myX = Case.findById(myCase.getId()+8).get().getX();
  		int myY = Case.findById(myCase.getId()+8).get().getY();
  		
  		boolean statusH = Case.findById(myCase.getId()+8).get().isEtat();
  		boolean statusHG = Case.findById(myCase.getId()+7).get().isEtat();
  		boolean statusHD = Case.findById(myCase.getId()+9).get().isEtat();
  		
  		if (statusH == true)
  			newCoulH = Case.findById(myCase.getId()+8).get().getPionCase().getCouleur();
  		
  		if (statusHG == true)
  			newCoulHG = Case.findById(myCase.getId()+7).get().getPionCase().getCouleur();
  		
  		if (statusHD == true)
  			newCoulHD = Case.findById(myCase.getId()+9).get().getPionCase().getCouleur();
  		
  		if(nX == myX && nY == myY++ && statusH == false) {
  			move = true;
  		}
  		else if (nX == myX++ && nY == myY++ && statusHD == true && newCoulHD.getNom() != myCoul.getNom()) {
  			move = true;
  		}
  		else if (nX == myX-- && nY == myY++ && statusHG == true && newCoulHG.getNom() != myCoul.getNom()) {
  			move = true;
  		}
  		
  		return move;
  	}
  	
  	// Verifier le deplacement de la tour
 	public boolean checkTour(Cases myCase, int nX, int nY) {
 		
 		boolean move = false;
 		
 		Couleur newCoul = null;
  		Couleur myCoul = myCase.getPionCase().getCouleur();
 		
 		int myX = Case.findById(myCase.getId()+8).get().getX();
  		int myY = Case.findById(myCase.getId()+8).get().getY();
  		
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
 					else if (status == true && i == nX-1 && newCoul.getNom() != myCoul.getNom()) {
 						
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
 					else if (status == true && i == nX+1 && newCoul.getNom() != myCoul.getNom()) {
 						
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
 					else if (status == true && i == nY-1 && newCoul.getNom() != myCoul.getNom()) {
 						
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
 					else if (status == true && i == nY+1 && newCoul.getNom() != myCoul.getNom()) {
 						
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
 		
 		int myX = Case.findById(myCase.getId()+8).get().getX();
  		int myY = Case.findById(myCase.getId()+8).get().getY();
  		
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
 					else if (status == true && i == nX-1 && newCoul.getNom() != myCoul.getNom()) {
 						
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
 					else if (status == true && i == nX+1 && newCoul.getNom() != myCoul.getNom()) {
 						
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
 					else if (status == true && i == nX-1 && newCoul.getNom() != myCoul.getNom()) {
 						
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
 					else if (status == true && i == nX+1 && newCoul.getNom() != myCoul.getNom()) {
 						
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
  		
  		int myX = Case.findById(myCase.getId()+8).get().getX();
   		int myY = Case.findById(myCase.getId()+8).get().getY();
   		
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
  					else if (status == true && i == nX-1 && newCoul.getNom() != myCoul.getNom()) {
  						
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
  					else if (status == true && i == nX+1 && newCoul.getNom() != myCoul.getNom()) {
  						
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
  					else if (status == true && i == nY-1 && newCoul.getNom() != myCoul.getNom()) {
  						
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
 					else if (status == true && i == nX-1 && newCoul.getNom() != myCoul.getNom()) {
 						
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
 					else if (status == true && i == nX+1 && newCoul.getNom() != myCoul.getNom()) {
 						
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
  					else if (status == true && i == nY+1 && newCoul.getNom() != myCoul.getNom()) {
  						
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
 					else if (status == true && i == nX-1 && newCoul.getNom() != myCoul.getNom()) {
 						
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
 					else if (status == true && i == nX+1 && newCoul.getNom() != myCoul.getNom()) {
 						
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
   		
   		int myX = Case.findById(myCase.getId()+8).get().getX();
   		int myY = Case.findById(myCase.getId()+8).get().getY();
   		
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
   			
   			if (statusH == true && newCoulH.getNom() != myCoul.getNom())
   				move = true;
   			else if (statusH == false)
   				move = true;
   		}
   		// Deplacement vers le haut et la droite
   		else if (nX == myX++ && nY == myY++) {
   			
   			if (statusHD == true && newCoulHD.getNom() != myCoul.getNom())
   				move = true;
   			else if (statusHD == false)
   				move = true;
   		}
   		// Deplacement vers le haut et la gauche
   		else if (nX == myX-- && nY == myY++) {

   			if (statusHG == true && newCoulHG.getNom() != myCoul.getNom())
   				move = true;
   			else if (statusHG == false)
   				move = true;
   		}
   		// Deplacement vers le bas
   		else if (nX == myX && nY == myY--) {
   			
   			if (statusB == true && newCoulB.getNom() != myCoul.getNom())
   				move = true;
   			else if (statusB == false)
   				move = true;
   		}
   		// Deplacement vers le bas et la droite
   		else if (nX == myX++ && nY == myY--) {
   			
   			if (statusBD == true && newCoulBD.getNom() != myCoul.getNom())
   				move = true;
   			else if (statusBD == false)
   				move = true;
   		}
   		// Deplacement vers le bas et la gauche
   		else if (nX == myX-- && nY == myY--) {
   			
   			if (statusBG == true && newCoulBG.getNom() != myCoul.getNom())
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
				else if (statusHD1 == true && newCoulHD1.getNom() != myCoul.getNom()) {
					move = true;
				}
				else if (statusHD2 == false) {
					move = true;
				}
				else if (statusHD2 == true && newCoulHD2.getNom() != myCoul.getNom()) {
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
				else if (statusHG1 == true && newCoulHG1.getNom() != myCoul.getNom()) {
					move = true;
				}
				else if (statusHG2 == false) {
					move = true;
				}
				else if (statusHG2 == true && newCoulHG2.getNom() != myCoul.getNom()) {
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
				else if (statusBD1 == true && newCoulBD1.getNom() != myCoul.getNom()) {
					move = true;
				}
				else if (statusBD2 == false) {
					move = true;
				}
				else if (statusBD2 == true && newCoulBD2.getNom() != myCoul.getNom()) {
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
				else if (statusBG1 == true && newCoulBG1.getNom() != myCoul.getNom()) {
					move = true;
				}
				else if (statusBG2 == false) {
					move = true;
				}
				else if (statusBG2 == true && newCoulBG2.getNom() != myCoul.getNom()) {
					move = true;
				}
  			}
  		}
 	
  		return move;
  	}
}
