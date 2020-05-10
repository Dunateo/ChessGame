package com.cir3.chessgame.domain;

import org.springframework.boot.autoconfigure.batch.JobExecutionEvent;

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

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Joueur joueurNoir;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(  updatable = false)
    private Set<Joueur> joueur;


    @OneToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
            },
            mappedBy = "partie")
    private List<Cases> table = new ArrayList<>();

}
