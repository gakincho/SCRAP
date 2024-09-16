package com.scrap.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scrap.entity.User;

@Controller
public class CommonController {

	@GetMapping("/login")
	public String getLogin(@ModelAttribute User user) {
		return "login";
	}
	
	@GetMapping("/")
	public String getIndex(@AuthenticationPrincipal User user, Model model) {
		model.addAttribute("user", user);
		return "index";
	}

}
