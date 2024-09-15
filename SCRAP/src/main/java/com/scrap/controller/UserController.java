package com.scrap.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.scrap.entity.User;

@Controller
public class UserController {
	
	@GetMapping("/")
	public String getIndex(@AuthenticationPrincipal User user, Model model) {
		model.addAttribute("user", user);
		return "index";
	}
	
}
