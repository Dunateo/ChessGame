package com.cir3.chessgame.Controller;

import com.cir3.chessgame.domain.Authority;
import com.cir3.chessgame.repository.AuthorityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAuthority {
    @Autowired
    private AuthorityRepository auth;

    @Test
    void creation() {
        Authority newAuth = new Authority();
        newAuth.setAuthority("ROLE_TEST");
        auth.save(newAuth);

        ArrayList<Authority> last = auth.findByAuthorityEquals("ROLE_TEST");

        assertEquals(newAuth.getId(), last.get(0).getId(), "L'id est different de celui crée");
    }
}
