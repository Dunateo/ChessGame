package com.cir3.chessgame.services;

import com.cir3.chessgame.domain.Friends;
import com.cir3.chessgame.domain.Joueur;
import com.cir3.chessgame.repository.JoueurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class FriendService {


    protected JoueurRepository joueur;

    @Autowired
    public FriendService(JoueurRepository player){this.joueur = player;}

    public String verifieFriendsList(Joueur player1, Joueur player2){
        String etatfriend = "0";
        if(player1.getFriends().getFriendsList() != null){
            System.out.println("friend list not null");
        if(player1.getFriends().getFriendsList().contains(player2)){
            System.out.println("Already your friend");
            etatfriend = "Erreur: C'est déjà votre amis";
        }
        }
        else{

            etatfriend = "OK";
        }
        return etatfriend;
    }
    public String verifieInviteList(Joueur player1, Joueur player2){
        String etatinvit = "0";
        if(player1.getFriends().getInviteList().contains(player2)){
            System.out.println("Already invited");
            etatinvit = "Erreur: Invitation déjà envoyée";
        }
        else{
            etatinvit = "OK";
        }
        return etatinvit;
    }

    public String addToInviteList(String playername1, String playername2){
        Joueur player1 = joueur.findByUsername(playername1);
        Joueur player2 = joueur.findByUsername(playername2);
        System.out.println(player1.getFriends());
        String message = "";
        if(player2 != null) {
            message = verifieFriendsList(player1, player2);
            System.out.println("paase ici");
            if (message.equals("OK")) {
                System.out.println("verif friend list ok");
                message = verifieInviteList(player1, player2);
                if (message.equals("OK")) {
                    System.out.println("verif invite list ok");
                    player1.getFriends().addInviteList(player2);
                    joueur.save(player1);
                }
            }
        }
        else{
            message = "pas de joueur 2";
        }
         return message;
    }

}
