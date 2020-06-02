package com.cir3.chessgame.form;


import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class JoueurForm {
    private Long id;

    @NotNull
    @Size(min=2, max = 30)
    private String username;

    @NotNull
    @Size(min=6, max = 60)
    private String password;

    private MultipartFile image;

    private Set<Long> partie = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        this.password = password;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public Set<Long> getPartie() {
        return partie;
    }

    public void setPartie(Set<Long> partie) {
        this.partie = partie;
    }
}
