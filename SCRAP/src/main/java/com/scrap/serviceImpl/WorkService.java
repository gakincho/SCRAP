package com.scrap.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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
	
	// 保存先固定パス指定
    private static final String UPLOAD_DIR_BOOK = "C:/github/SCRAP/SCRAP/src/main/resources/static/img/book/";
    private static final String UPLOAD_DIR_MOVIE = "C:/github/SCRAP/SCRAP/src/main/resources/static/img/movie/";
    
    @Autowired
	BookRepository bookRepos;
	
    @Autowired
	MovieRepository movieRepos;
	
	// 全件取得(書籍情報)
	public List<Book> findAllBooks(){
		List<Book> allBooks = bookRepos.findAll();
		allBooks.forEach(book -> book.setWorkType(true));
		return allBooks;
	}
	
	// 全件取得(映像情報)
	public List<Movie> findAllMovies(){
		List<Movie> allMovies = movieRepos.findAll();
		allMovies.forEach(book -> book.setWorkType(false));
		return allMovies;
	}
	
	// 全件取得(シャッフル)
	public List<Work> shuffleAllWork(List<? extends Work> bookList, List<? extends Work> movieList) {
		List<Work> shuffleAllWork = new ArrayList<>();
		shuffleAllWork.addAll(bookList);
		shuffleAllWork.addAll(movieList);
		Collections.shuffle(shuffleAllWork);
		return shuffleAllWork;
	}
	
	// 画像保存、DB登録用パス取得
	public String rgstImg(Work work){
		String fileName = null;
		MultipartFile file = work.getFile();
		File savedFile = null;
		try {
			// 書籍情報の場合
			if(work.getWorkType()) {
				fileName = "b" + (String.format("%05d",bookRepos.findMaxBookId()+1)) + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
				savedFile = new File(UPLOAD_DIR_BOOK + fileName);
			// 映像情報の場合
			} else {
				fileName = "m" + (String.format("%05d",movieRepos.findMaxMovieId()+1)) + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));				
				savedFile = new File(UPLOAD_DIR_MOVIE + fileName);
			}
			file.transferTo(savedFile);
		} catch(IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}
	
	// 作品登録
	
	public void save(Work work) {
		// idがnull(書籍) || idが存在する(映像)
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
