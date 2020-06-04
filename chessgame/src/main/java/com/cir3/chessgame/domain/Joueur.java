package com.cir3.chessgame.domain;

import com.cir3.chessgame.component.BCryptManagerUtil;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity(name = "joueur")
public class Joueur implements UserDetails {
    private static final long serialVersionUID = -2963008589618789228L;

    public static interface JoueurView{
        public static interface BasicData{}
        public static interface ExtendedData extends BasicData{}
    }

    @Id
    @Column
    @GeneratedValue(generator = "seqUser")
    @SequenceGenerator(name = "seqUser", sequenceName = "seq_user")
    @JsonView(JoueurView.BasicData.class)
    private Long id;

    @Column(nullable = false,
            unique = true,
            length = 100)
    @JsonView(JoueurView.BasicData.class)
    private String username;

    @Column(nullable = false,
            length = 100)
    @JsonView(JoueurView.ExtendedData.class)
    private String password;


    @Column
    @JsonView(JoueurView.BasicData.class)
    private String image;


    @OneToOne(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
            },
            mappedBy = "player")
    private Friends friends;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
            },
            mappedBy = "joueur")
    @JsonView(JoueurView.ExtendedData.class)
    private Set<Partie> partie = new HashSet<>();

    @ManyToMany(fetch=FetchType.EAGER)
    @JsonView(JoueurView.ExtendedData.class)
    private Collection<Authority> authorities = new ArrayList<>();

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (!password.isEmpty()) {
            this.password = BCryptManagerUtil.passwordencoder().encode(password);
        }
    }


    public Set<Partie> getPartie() {
        return partie;
    }

    public void setPartie(Set<Partie> partie) {
        this.partie = partie;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Collection<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<Authority> authorities) {
        this.authorities = authorities;
    }

    public void setOneAuthorities(Authority auth){
        this.authorities.add(auth);
    }

    public Friends getFriends() { return friends; }

    public void setFriends(Friends friends) { this.friends = friends; }


    public String getInvitationList() {
    	StringBuilder l= new StringBuilder();
    	Iterator<Partie> it= partie.iterator();
    	while(it.hasNext()){
    		Partie p=it.next();
    		//On cherche toute les parties en mode invitation sauf nos invitation 
            if(p.getTour() == -1 && !p.getJoueurNoir().getUsername().equals(username)) {
            	l.append("/").append(p.getId()).append(".").append(p.getJoueurNoir().getUsername());
            }
         }
    	
    	return l.toString();
    }
   

}
