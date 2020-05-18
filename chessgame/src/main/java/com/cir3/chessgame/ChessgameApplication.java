package com.cir3.chessgame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ChessgameApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChessgameApplication.class, args);
	}

}
