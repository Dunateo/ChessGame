package com.cir3.chessgame.Controller;

import com.cir3.chessgame.domain.Authority;
import com.cir3.chessgame.domain.Joueur;
import com.cir3.chessgame.repository.AuthorityRepository;
import com.cir3.chessgame.repository.JoueurRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.JobExecutionEvent;

import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestJoueur {

    @Autowired
    private JoueurRepository user;

    @Autowired
    private AuthorityRepository auth;


    @Test
    void login() {
    }

    @Test
    void creation() {
        Joueur j1 = new Joueur();
        Optional<Authority> maybeAuth = auth.findById(Integer.toUnsignedLong(52));

        if (maybeAuth.isPresent()){

            j1.setOneAuthorities(maybeAuth.get());
            j1.setImage(null);
            j1.setUsername("Joueur1");
            j1.setPassword("jesuisj1");
            user.save(j1);

            Joueur j2 = user.findByUsername("Joueur1");

            assertEquals(j1.getId(), j2.getId(), "L'id est different");
            assertEquals(j1.getUsername(), j2.getUsername(), "Le nom n'est pas identique");
            assertEquals(j1.getPassword(), j2.getPassword(), "les mots de passes dont differents");
            assertEquals(j1.getAuthorities(), j2.getAuthorities(), "l'authorité est differente");
        }else {
            System.out.println("Il n'y a rien dans le retour l'Optional");
        }
        }


}
