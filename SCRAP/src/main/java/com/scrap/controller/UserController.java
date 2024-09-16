package com.scrap.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.scrap.entity.User;

@Controller
public class UserController {
	
	@GetMapping("/user")
	public String getUserMenu(@AuthenticationPrincipal User user, Model model) {
		model.addAttribute("user", user);
		return "user";
	}
	
	@GetMapping("/user/rgst")
	public String getUserRgst(@AuthenticationPrincipal User user, Model model) {
		model.addAttribute("user", user);
		return "user_rgst";
	}
	
	@GetMapping("/user/dlt")
	public String getUserDlt(@AuthenticationPrincipal User user, Model model) {
		model.addAttribute("user", user);
		return "user_dlt";
	}
	
	
	
}
