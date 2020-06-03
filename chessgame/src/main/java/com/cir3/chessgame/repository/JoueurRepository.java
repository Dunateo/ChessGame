package com.cir3.chessgame.repository;

import com.cir3.chessgame.domain.Joueur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface JoueurRepository extends JpaRepository<Joueur,Long> {

    /*List<Joueur> friendList = new ArrayList<>();


    List<Joueur> findAll(){return friendList;}*/



    Joueur findByUsername(String username);
}
