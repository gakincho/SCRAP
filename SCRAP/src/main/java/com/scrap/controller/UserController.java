package com.scrap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.scrap.entity.User;
import com.scrap.serviceImpl.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userSev;
	
	// ユーザーページの取得
	@GetMapping("/profile")
	public String getUserMenu(@AuthenticationPrincipal User user, Model model) {
		model.addAttribute("user", user);
		return "profile";
	}
	
	// アカウント登録ページの取得
	@GetMapping("/account/rgst")
	public String getUserRgst(@AuthenticationPrincipal User user, Model model) {
		model.addAttribute("user", user);
		model.addAttribute("rgstUser", new User());
		return "accountRgst";
	}
	
	// アカウント削除画面の取得
	@GetMapping("/account/dlt")
	public String getUserDlt(@AuthenticationPrincipal User user, Model model) {
		model.addAttribute("user", user);
		return "accountDlt";
	}
	
	// アカウント登録情報受付
	@PostMapping("/account/rgst")
	public String postUserRgst(@ModelAttribute("rgstUser") User rgstUser, BindingResult result, @AuthenticationPrincipal User user, Model model) {
		model.addAttribute("user", user);
	    if (result.hasErrors()) {
	        return "registrationForm"; // エラーがあった場合の再表示
	    }
	    userSev.save(rgstUser);
		return "redirect:/profile";
	}
	
}
