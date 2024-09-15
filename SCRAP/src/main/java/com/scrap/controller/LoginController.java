package com.scrap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scrap.entity.User;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String getLogin(@ModelAttribute User user) {
		return "login";
	}

}
