package com.scrap.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.scrap.entity.Book;
import com.scrap.entity.Movie;
import com.scrap.entity.User;
import com.scrap.entity.Work;
import com.scrap.serviceImpl.GenreService;
import com.scrap.serviceImpl.WorkService;

@Controller
public class WorksController {

	@Autowired
	GenreService genreSev;
	
	@Autowired
	WorkService workSev;
	
	// 作品一覧画面
	@GetMapping("/works")
	public String getWorks(@AuthenticationPrincipal User user, Model model) {
		model.addAttribute("user", user);
		List<Book> books = workSev.findAllBooks();
		List<Movie> movies = workSev.findAllMovies();
		List<Work> works = workSev.shuffleAllWork(books, movies);
		model.addAttribute("works", works);
		return "works";
	}
	
	// 書籍情報登録画面
	@GetMapping("/work/rgst")
	public String getWorkRgst(@AuthenticationPrincipal User user, Model model) {
		model.addAttribute("user", user);
		model.addAttribute("genres", genreSev.findGenres());
		model.addAttribute("rgstWork", new Work());
		return "workRgst";
	}
	
	//書籍・映像情報登録
	@PostMapping("/work/rgst")
	public String postWorkRgst(@ModelAttribute("rgstWork") Work rgstWork, BindingResult result, @AuthenticationPrincipal User user, Model model) {
		model.addAttribute("user", user);
		String fileName = null;
		if (result.hasErrors()) {
			model.addAttribute("user", user);
			model.addAttribute("genres", genreSev.findGenres());
			model.addAttribute("rgstWork", new Work());
	        return "workRgst";
	    }
	    // workTypeに基づいて適切なインスタンスを生成
	    Work specificWork = rgstWork.getWorkType() ? new Book() : new Movie();

	    // rgstWorkのプロパティをspecificWorkにコピー
	    BeanUtils.copyProperties(rgstWork, specificWork, "id");
	    
        if (!specificWork.getFile().isEmpty()) {
        	fileName = workSev.rgstImg(specificWork);
        	specificWork.setImgPath(fileName);
        }
        workSev.save(specificWork);
        
		List<? extends Work> works = workSev.findAllBooks();
		model.addAttribute("works", works);
	    return "works";
	}
	
}
