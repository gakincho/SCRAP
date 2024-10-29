package com.scrap.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "book", schema = "scrap")
public class Book extends Work{
}
