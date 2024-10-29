package com.scrap.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scrap.entity.Genre;
import com.scrap.repository.GenreRepository;

@Service
public class GenreService {
	
	@Autowired
	GenreRepository genreRepos;
	
	//全件取得
	public List<Genre> findGenres(){
		return genreRepos.findByDeleteFlagFalse();
	}
	
}
