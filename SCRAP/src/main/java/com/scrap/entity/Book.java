package com.scrap.entity;

import java.sql.Timestamp;

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
@Table(name = "book", schema = "scrap")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne()
    @JoinColumn(name = "genre", referencedColumnName = "id")
    private Genre genre; 
    
    @Column(name = "author")
    private String author;

    @Column(name = "created")
    private Timestamp created;

    @Column(name = "modified")
    private Timestamp modified;

    @Column(name = "delete_flag")
    private Boolean deleteFlag = false;
    
    @Column(name = "img_path")
    private String imgPath;
}
