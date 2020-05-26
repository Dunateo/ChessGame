package com.cir3.chessgame.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.File;
import java.nio.file.Files;

@Controller
@Secured("ROLE_USER")
public class ImageController {
    @Value("${file.upload-dir:}")
    private String path ;

    private static Logger logger = LogManager.getLogger(ImageController.class);


    @GetMapping("/user/avatar")
    public ResponseEntity<StreamingResponseBody> getAvatar(@RequestParam String filename, Authentication authentication) {

        File file = new File(path + filename);
        StreamingResponseBody responseBody = outputStream -> {
            Files.copy(file.toPath(), outputStream);
        };

        logger.info("Recuperation d'une image "+filename );

        return ResponseEntity.ok().body(responseBody);
    }



}
