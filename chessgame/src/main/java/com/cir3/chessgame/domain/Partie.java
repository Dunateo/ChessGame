package com.cir3.chessgame.domain;

import javax.persistence.*;
import java.util.*;

@Entity(name = "partie")
public class Partie {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean etat;

    @Column
    private int Tour;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date fixtemps;

    @Column
    private int duree;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(  updatable = false)
    private List<Joueur> joueur;


    @OneToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
            },
            mappedBy = "partie")
    private List<Cases> table = new ArrayList<>();

}
