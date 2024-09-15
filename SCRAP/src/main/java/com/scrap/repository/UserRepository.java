package com.scrap.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scrap.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByMail(String mail);
}
