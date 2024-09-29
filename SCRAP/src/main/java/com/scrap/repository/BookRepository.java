package com.scrap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scrap.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
