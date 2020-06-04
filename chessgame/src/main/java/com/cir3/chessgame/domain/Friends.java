package com.cir3.chessgame.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity(name = "friends")
public class Friends {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Joueur player;

    @ManyToMany
    private List<Joueur> friendsList = new ArrayList<>();

    @ManyToMany
    private List<Joueur> inviteList= new ArrayList<>();

    public List<Joueur> getFriendsList() { return friendsList; }

    public void setFriendsList(List<Joueur> friendsList) { this.friendsList = friendsList; }

    public void addFriendsList(Joueur player){ this.friendsList.add(player); }

    public List<Joueur> getInviteList() { return inviteList; }

    public void setInviteList(List<Joueur> inviteList) { this.inviteList = inviteList; }

    public void addInviteList(Joueur player){ this.inviteList.add(player); }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Joueur getPlayer() { return player; }

    public void setPlayer(Joueur player) { this.player = player; }

    public void deleteInviteList(Joueur player) { this.inviteList.remove(player); }
}
