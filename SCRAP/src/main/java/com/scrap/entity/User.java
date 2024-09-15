package com.scrap.entity;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user", schema = "scrap")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "mail")
    private String mail;

    @Column(name = "name")
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "password")
    private String password;

    @Column(name = "authority")
    private Boolean authority;

    @Column(name = "created")
    private Timestamp created;

    @Column(name = "modified")
    private Timestamp modified;

    @Column(name = "delete_flag")
    private Boolean delete_Flag = false;

    // UserDetails のメソッドをオーバーライド

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // authority が true の場合に "ROLE_ADMIN" を付与し、false の場合 "ROLE_USER" とする例
        String role = authority ? "ROLE_ADMIN" : "ROLE_USER";
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.mail; // メールアドレスをユーザー名として使用
    }
    
    @Override
    public boolean isEnabled() {
        // ユーザーが削除されていないかどうかをチェック
        return !this.delete_Flag;
    }

//    @Override
//    public boolean isAccountNonExpired() {
//        // 有効期限がある場合はここでチェック。
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        // アカウントがロックされているかどうかをチェック。
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        // 資格情報（パスワード）が期限切れかどうかチェック。
//        return true;
//    }
}
