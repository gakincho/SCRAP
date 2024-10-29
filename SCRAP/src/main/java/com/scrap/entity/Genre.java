package com.scrap.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "genre", schema = "scrap")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genre {
	/*
	 * ジャンルid
	 * 自動採番
	 */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    /*
     * 作品名
     */
    @Column(name = "name")
    private String name;
    
    /*
     * 削除フラグ
     */
    @Column(name = "delete_flag")
    private Boolean deleteFlag = false;
    
    /*
     * 作品タイプ
     * 1:共通
     * 2:書籍
     * 3:映像
     */
    @ManyToOne()
    @JoinColumn(name = "worktype", referencedColumnName = "id")
    private WorkType worktype;
}
