package com.cir3.chessgame.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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


    @GetMapping("/user/avatar")
    public ResponseEntity<StreamingResponseBody> getAvatar(@RequestParam String filename) {

        File file = new File(path + filename);
        StreamingResponseBody responseBody = outputStream -> {
            Files.copy(file.toPath(), outputStream);
        };

        return ResponseEntity.ok().body(responseBody);
    }



}
