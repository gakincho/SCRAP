package com.scrap.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scrap.entity.Book;
import com.scrap.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepos;
	
	//全件取得
	public List<Book> findAllBooks(){
		return bookRepos.findAll();
	}
	
}
