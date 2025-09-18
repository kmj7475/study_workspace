package com.example.demo.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="books")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 6, nullable = false)
    private Integer bookNo;

    @Column(length = 100)
    private String bookName;

    @Column(length = 100)
    private String author;

    @Column(length = 100)
    private String company;

    @Column(length = 100, columnDefinition = "NUMBER(10) DEFAULT 0")
    private String price;

    @Column(columnDefinition = "DATE DEFAULT SYSDATE")
    private Date publishDate;

    @Column(length = 100)
    private String category;

    @Column(length = 1000)
    private String about;

    @Column(length = 100)
    private String image;

    @Column(length = 50)
    private String isbn;

    @Column(length = 6)
    private Integer empNo;
}
  