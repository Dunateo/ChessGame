package com.cir3.chessgame.services;

import com.cir3.chessgame.domain.Friends;
import com.cir3.chessgame.domain.Joueur;
import com.cir3.chessgame.repository.FriendsRepository;
import com.cir3.chessgame.repository.JoueurRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FriendService {


    protected JoueurRepository joueur;

    protected FriendsRepository friends;

    private static Logger logger = LogManager.getLogger(FriendService.class);

    @Autowired
    public FriendService(JoueurRepository player, FriendsRepository friends){
        this.joueur = player;
        this.friends = friends;
    }


    public String verifieFriendsList(Joueur player1, Joueur player2){
        String etatfriend = "0";

        //on remedie au null
        if(player2.getFriends() == null){
            //creation friends
            Friends friendsList = new Friends();
            friendsList.setPlayer(player2);

            //save and update
            friends.saveAndFlush(friendsList);

            //assign une liste d'amis au destinataire
            player2.setFriends(friendsList);
            joueur.save(player2);

            etatfriend = "Céation Liste d'invitation";
        }

        if(!player2.getFriends().getFriendsList().contains(player1) && !verifieInviteList(player1, player2)){

            logger.debug("Already your friend");
            etatfriend = "Erreur: C'est déjà votre amis";

        }else {
            //ajout dans la liste d'invitation
            player2.getFriends().addInviteList(player1);
            joueur.save(player2);

            etatfriend = "OK";
        }

        return etatfriend;
    }

    public Boolean verifieInviteList(Joueur player1, Joueur player2){

        if(player2.getFriends().getInviteList().contains(player1)){
            System.out.println("Already invited");

           return  Boolean.FALSE;

        }

        return Boolean.TRUE;
    }

    public String addToInviteList(String playername1, String playername2){

        Joueur player1 = joueur.findByUsername(playername1);
        Joueur player2 = joueur.findByUsername(playername2);

        System.out.println(player1.getFriends());
        String message = "";

        if(player2 != null) {
            message = verifieFriendsList(player1, player2);

            if (message.equals("OK") && !playername1.equals(playername2) ) {
                System.out.println("verif friend list ok");
            }

        } else{

            message = "pas de joueur 2";
        }

         return message;
    }

    public Boolean addToFriendList(String playername1, String playername2) {
        Joueur player1 = joueur.findByUsername(playername1);
        Joueur player2 = joueur.findByUsername(playername2);

        String message = "";

        if(player2 != null) {

            message = verifieFriendsList(player1, player2);
            if (message.equals("Erreur: C'est déjà votre amis") && !playername1.equals(playername2)) {

                        System.out.println("verif friend list ok");

                        player1.getFriends().addFriendsList(player2);
                        player1.getFriends().deleteInviteList(player2);
                        joueur.save(player1);

                        player2.getFriends().addFriendsList(player1);
                        player2.getFriends().deleteInviteList(player1);
                        joueur.save(player2);

                        return Boolean.TRUE;
                    }
            }
        //System.out.println(message);
        return Boolean.FALSE;

    }

}
