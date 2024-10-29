package com.scrap.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "movie", schema = "scrap")
public class Movie extends Work{
}
