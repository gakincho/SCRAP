package com.scrap.entity;

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
@Table(name = "user", schema = "genre")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genre {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "work_type")
    private String work_type;

    
    @Column(name = "name")
    private String name;
    
    @Column(name = "delete_flag")
    private Boolean delete_Flag = false;
}
