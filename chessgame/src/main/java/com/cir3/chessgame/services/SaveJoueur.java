package com.cir3.chessgame.services;

import com.cir3.chessgame.domain.Joueur;
import com.cir3.chessgame.form.EditForm;
import com.cir3.chessgame.form.JoueurForm;
import com.cir3.chessgame.repository.AuthorityRepository;
import com.cir3.chessgame.repository.JoueurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class SaveJoueur {


    protected final JoueurRepository joueur;

    protected final AuthorityRepository autho;

    @Autowired
    public SaveJoueur(JoueurRepository joueur, AuthorityRepository autho){
        this.joueur = joueur;
        this.autho = autho;
    }

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
    public boolean editPasswordJoueur(EditForm form){

        Joueur tab = joueur.findByUsername(form.getUsername());

        if (tab != null){
            tab.setPassword(form.getPassword());
            joueur.save(tab);
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
    public boolean editImageJoueur(MultipartFile image, EditForm form, String FOLDER_UPLOAD){

        ImageStock storage = new ImageStock();
        //Si on arrive a enregistrer l'image alors on enregistre notre utilisateur
        if (storage.upload(image, form.getUsername(),FOLDER_UPLOAD)){
            Joueur tab = joueur.findByUsername(form.getUsername());

            if (tab != null){
                tab.setImage(FOLDER_UPLOAD + form.getUsername());
                joueur.save(tab);
                return Boolean.TRUE;
            }else {
                return Boolean.FALSE;
            }

        }
        return Boolean.FALSE;
    }
}
