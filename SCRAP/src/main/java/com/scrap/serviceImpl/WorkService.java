package com.scrap.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.scrap.entity.Book;
import com.scrap.entity.Movie;
import com.scrap.entity.Work;
import com.scrap.repository.BookRepository;
import com.scrap.repository.MovieRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class WorkService {
	
	// 保存先パス指定
    private static final String UPLOAD_DIR = "C:/github/SCRAP/SCRAP/src/main/resources/static/img/img/";
	
    @Autowired
	BookRepository bookRepos;
	
    @Autowired
	MovieRepository movieRepos;
	
	// 全件取得
	public List<Book> findAllBooks(){
		List<Book> all = bookRepos.findAll();
		all.forEach(book -> book.setWorkType(true));
		return all;
	}
	
	// 画像保存、DB登録用パス取得
	public String rgstImg(MultipartFile file,String title){
		String fileName = null;
		try {
			fileName = title + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
			File savedFile = new File(UPLOAD_DIR + fileName);
			new File(UPLOAD_DIR).mkdirs();
			file.transferTo(savedFile);
		} catch(IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}
	
	// 作品登録
	public void save(Work work) {
		System.out.println("work instance: " + work.getClass().getName());

		if(work.getId()==null||work.getId()<1) {
			work.setCreated(Timestamp.valueOf(LocalDateTime.now()));
		}
		
		if (work instanceof Book) { 
		    bookRepos.save((Book) work); 
		} else if(work instanceof Movie) { 
			movieRepos.save((Movie) work);
	    } else { 
	        throw new IllegalArgumentException("Unsupported work type: " + work.getClass().getName()); 
	    }
	}
}
