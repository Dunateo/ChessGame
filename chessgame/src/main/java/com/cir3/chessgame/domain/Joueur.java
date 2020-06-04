package com.cir3.chessgame.domain;

import com.cir3.chessgame.component.BCryptManagerUtil;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity(name = "joueur")
public class Joueur implements UserDetails {
    private static final long serialVersionUID = -2963008589618789228L;

    @Id
    @Column
    @GeneratedValue(generator = "seqUser")
    @SequenceGenerator(name = "seqUser", sequenceName = "seq_user")
    private Long id;

    @Column(nullable = false,
            unique = true,
            length = 100)
    private String username;

    @Column(nullable = false,
            length = 100)
    private String password;


    @Column
    private String image;


    @OneToOne(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
            },
            mappedBy = "player")
    private Friends friends = new Friends();




    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
            },
            mappedBy = "joueur")
    private Set<Partie> partie = new HashSet<>();

    @ManyToMany(fetch=FetchType.EAGER)
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



}
