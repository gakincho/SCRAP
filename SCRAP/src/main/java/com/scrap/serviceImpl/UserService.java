package com.scrap.serviceImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scrap.entity.User;
import com.scrap.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepos;
	
    @Autowired
    private PasswordEncoder passwordEncoder;
	
	public void save(User user) {
		if(user.getId()==null||user.getId()<1) {
			user.setCreated(Timestamp.valueOf(LocalDateTime.now()));
		}
        // パスワードをハッシュ化
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        userRepos.save(user);
	}
	
}
