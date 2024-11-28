package com.scrap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scrap.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    @Query("SELECT MAX(e.id) FROM Movie e")
    Integer findMaxMovieId();
}
