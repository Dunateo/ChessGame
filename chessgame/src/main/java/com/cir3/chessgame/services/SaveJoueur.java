package com.cir3.chessgame.services;

import com.cir3.chessgame.domain.Joueur;
import com.cir3.chessgame.form.JoueurForm;
import com.cir3.chessgame.repository.AuthorityRepository;
import com.cir3.chessgame.repository.JoueurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class SaveJoueur {

    @Autowired
    private JoueurRepository joueur;

    @Autowired
    private AuthorityRepository autho;

    /**
     * CREATE A PLAYER IN BDD
     * @param image
     * @param form
     * @param FOLDER_UPLOAD
     * @return
     */
    public boolean createJoueur(MultipartFile image, JoueurForm form, String FOLDER_UPLOAD){
        ImageStock storage = new ImageStock();

        //Si on arrive a enregistrer l'image alors on enregistre notre utilisateur
        if (storage.upload(image, form.getUsername(),FOLDER_UPLOAD)){
            //attribution du formulaire à une entities
            Joueur joueurNew = new Joueur();
            joueurNew.setUsername(form.getUsername());
            joueurNew.setPassword(form.getPassword());
            joueurNew.setImage(FOLDER_UPLOAD + form.getUsername());
            joueurNew.setAuthorities(autho.findByAuthorityEquals("ROLE_USER"));
            joueur.save(joueurNew);

            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    /**
     * RETRIEVE THE PLAYER AND SAVE THE NEW PASSWORD
     * @param form
     * @return
     */
    public boolean editPasswordJoueur(JoueurForm form){

        Optional<Joueur> tab = joueur.findById(form.getId());

        if (tab.isPresent()){
            Joueur j1 = tab.get();
            j1.setPassword(form.getPassword());
            joueur.save(j1);
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    /**
     * EDIT THE IMAGE OF A PLAYER
     * @param image
     * @param form
     * @param FOLDER_UPLOAD
     * @return
     */
    public boolean editImageJoueur(MultipartFile image, JoueurForm form, String FOLDER_UPLOAD){

        ImageStock storage = new ImageStock();
        //Si on arrive a enregistrer l'image alors on enregistre notre utilisateur
        if (storage.upload(image, form.getUsername(),FOLDER_UPLOAD)){
            Optional<Joueur> tab = joueur.findById(form.getId());

            if (tab.isPresent()){
                Joueur j1 = tab.get();
                j1.setImage(FOLDER_UPLOAD + form.getUsername());
                joueur.save(j1);
                return Boolean.TRUE;
            }else {
                return Boolean.FALSE;
            }

        }
        return Boolean.FALSE;
    }
}
