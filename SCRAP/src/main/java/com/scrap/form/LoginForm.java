package com.scrap.form;

import lombok.Data;

@Data
public class LoginForm {
	
	/** ユーザー名 */
	 private String mail;
	 /** パスワード */
	 private String password;
}
