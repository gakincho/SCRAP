package com.scrap.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Work {
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
    private Timestamp modified = Timestamp.valueOf(LocalDateTime.now());

    @Column(name = "delete_flag")
    private Boolean deleteFlag = false;
    
    @Column(name = "img_path")
    private String imgPath;
    
    
    /*
     * true :書籍情報
     * false:映像情報
     */
    @Transient
    private Boolean workType;
    
    @Transient
    private MultipartFile file;
}
