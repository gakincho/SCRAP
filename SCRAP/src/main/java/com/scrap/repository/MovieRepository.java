package com.scrap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scrap.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
