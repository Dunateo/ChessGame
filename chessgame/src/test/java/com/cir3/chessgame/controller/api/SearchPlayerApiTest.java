package com.cir3.chessgame.controller.api;

import com.cir3.chessgame.domain.Joueur;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SearchPlayerApiTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void givenAuthRequestOnPrivateService_shouldSucceedWith200() throws Exception {
        ResponseEntity<String> result = template.withBasicAuth("spring", "secret")
                .getForEntity("/api/joueur/V", String.class);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testClassic(){
        ResponseEntity<String> result = template.withBasicAuth("spring", "secret")
                .getForEntity("/api/joueur/", String.class);

        assertEquals(HttpStatus.OK, result.getStatusCode());

    }

}
