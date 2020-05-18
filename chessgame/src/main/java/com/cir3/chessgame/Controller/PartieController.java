package com.cir3.chessgame.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/partie")
public class PartieController {
	@GetMapping("")
	public String partie() {
		return "partie";
	}

}
