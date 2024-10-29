package com.scrap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scrap.entity.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
	List<Genre> findByDeleteFlagFalse();
}
