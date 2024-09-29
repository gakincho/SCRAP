package com.scrap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.scrap.entity.Book;
import com.scrap.entity.User;
import com.scrap.serviceImpl.BookService;

public class WorksController {

	@Autowired
	BookService bookSev;
	
	//作品一覧画面
	@GetMapping("/works")
	public String getWorks(@AuthenticationPrincipal User user,Book book, Model model) {
		model.addAttribute("user", user);
		List<Book> books = bookSev.findAllBooks();
		model.addAttribute("books", books);
		return "works";
	}
	
	//書籍情報登録画面
	
}
