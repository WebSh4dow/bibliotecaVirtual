package com.biblioteca.aluguel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/login")
	public String login() {
	 return "login";
	}
	
	@GetMapping("/home")
    public String home() {
        return "home";
    }

}