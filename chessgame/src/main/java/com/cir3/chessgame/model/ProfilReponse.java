package com.cir3.chessgame.model;

import com.cir3.chessgame.domain.Joueur;
import com.cir3.chessgame.domain.Partie;

import java.util.ArrayList;
import java.util.List;

public class ProfilReponse {

    private List<Joueur> friends;

    private List<Joueur> inviteFriends;

    private List<PartieReponse> invitationPartie = new ArrayList<PartieReponse>();

    private Boolean invitationAccepted;

    private Long idPartieRequest;

    public ProfilReponse(){
    }

    public List<Joueur> getFriends() {
        return friends;
    }

    public void setFriends(List<Joueur> friends) {
        this.friends = friends;
    }

    public List<Joueur> getInviteFriends() {
        return inviteFriends;
    }

    public void setInviteFriends(List<Joueur> inviteFriends) {
        this.inviteFriends = inviteFriends;
    }

  
    public List<PartieReponse> getInvitationPartie() {
		return invitationPartie;
	}

	public void setInvitationPartie(List<PartieReponse> invitationPartie) {
		this.invitationPartie = invitationPartie;
	}

	public void addInvitationPartie(Long id,String versus) {
    	this.invitationPartie.add(new PartieReponse(id,versus));
    }

    public Boolean getInvitationAccepted() {
        return invitationAccepted;
    }

    public void setInvitationAccepted(Boolean invitationAccepted) {
        this.invitationAccepted = invitationAccepted;
    }

    public Long getIdPartieRequest() {
        return idPartieRequest;
    }

    public void setIdPartieRequest(Long idPartieRequest) {
        this.idPartieRequest = idPartieRequest;
    }
}
