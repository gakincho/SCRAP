package com.scrap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scrap.entity.User;
import com.scrap.entity.Work;
import com.scrap.serviceImpl.WorkService;

@Controller
public class CommonController {
	
	@Autowired
	WorkService bookSev;
	
	//ログイン画面
	@GetMapping("/login")
	public String getLogin(@ModelAttribute User user) {
		return "login";
	}
	
	//ホーム画面
	@GetMapping("/")
	public String getIndex(@AuthenticationPrincipal User user, Work work, Model model) {
		model.addAttribute("user", user);
		List<? extends Work> works = bookSev.findAllBooks();
		model.addAttribute("works", works);
		return "index";
	}
}
