package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/KMA")
public class KMA_controller {

	@GetMapping("/mid")
	public String KMA_mid() {
		
        return "KMA/mid_main";
	}
	
	@GetMapping("/short")
	public String KMA_short() {
		
        return "KMA/short_main";
	}
	
}
