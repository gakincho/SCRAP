package com.scrap.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scrap.entity.User;
import com.scrap.repository.UserRepository;

/**
 * カスタム認証サービス
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepos;
	
	@Autowired
    PasswordEncoder passwordEncoder;  // 依存注入
	
	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		
		//DBから入力内容に基づいたユーザー情報を取得
		Optional<User> optUser = userRepos.findByMail(mail);
		
		//ユーザー名が入力されるとUserDetailsの実装クラスを返す
		if(optUser.isPresent()) {
			User user = optUser.get();
			//対象データが存在する
			//UserDetailsの実装クラスを返す
			return user;
		} else {
			System.out.println("mailの値:"+mail);
			//対象データが存在しない
			//例外を投げる
			throw new UsernameNotFoundException(
					mail + " => ユーザーは存在しません");
		}
	}
	
	public boolean authenticate(String inputPassword, User user) {
	    // PasswordEncoderを使用して比較する
	    if (passwordEncoder.matches(inputPassword, user.getPassword())) {
	        System.out.println("認証成功");
	        return true;
	    } else {
	        System.out.println("認証失敗: パスワードが一致しません");
	        return false;
	    }
	}
	
}
