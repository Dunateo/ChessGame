package com.cir3.chessgame.services;

import com.cir3.chessgame.domain.Couleur;
import com.cir3.chessgame.domain.Pion;
import com.cir3.chessgame.repository.CouleurRepository;
import com.cir3.chessgame.repository.PionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class InitBddService {

    protected PionRepository pions;

    protected CouleurRepository couleurs;

   
    private String FOLDER_PION;

    @Autowired
    public InitBddService(PionRepository pions, CouleurRepository couleurs){
        this.pions = pions;
        this.couleurs = couleurs;
    }
    
    

	public void setFOLDER_PION(String fOLDER_PION) {
		FOLDER_PION = fOLDER_PION;
	}



	/**
     * crée une couleur
     * @param nom
     * @return
     */
    public Boolean createCouleurs(String nom){

        if (!nom.isEmpty()){
            Couleur couleur = new Couleur();
            couleur.setNom(nom);
            couleurs.save(couleur);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * crée un pion
     * @param nomPion
     * @param nomCouleur
     * @return
     */
    public Boolean createPion(String nomPion, String nomCouleur){

        if (!nomPion.isEmpty() && !nomCouleur.isEmpty()){
            Pion pion = new Pion();
            Couleur col = couleurs.findCouleurByNom(nomCouleur);
            if (col !=  null){
                pion.setCouleur(col);
                pion.setNom(nomPion);
                pion.setImage(FOLDER_PION+nomPion+col.getNom());
                pions.save(pion);

                return Boolean.TRUE;
            }else {
                return Boolean.FALSE;
            }
        }

        return Boolean.FALSE;
    }

    /**
     * crée a partir d'un tableau de multiple pions
     * @param tabnomPion
     * @param nomCouleur
     * @return
     */
    public Boolean createMultiPions(ArrayList<String> tabnomPion, String nomCouleur ){

        for (String pion : tabnomPion){
            if (!this.createPion(pion, nomCouleur)){
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }
}
