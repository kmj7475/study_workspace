package com.example.demo.domain;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Table(name = "books")
@Data
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "book_no", length = 6, nullable = false)
  private Integer bookNo;

  @Column(length = 100)
  private String bookName;

  @Column(length = 100)
  private String author;

  @Column(length = 100)
  private String company;

  @Column(length = 100, columnDefinition = "NUMBER(10) DEFAULT 0")
  private Long price;

  @Column(columnDefinition = "DATE DEFAULT SYSDATE")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date publishDate;

  @Column(length = 100)
  private String category;

  @Column(length = 1000)
  private String about;

  @Column(length = 100)
  private String image;

  @Column(length = 50)
  private String isbn;

  @ManyToOne
  @JoinColumn(name = "emp_no")
  private Employee employee;

  @OneToMany(mappedBy = "book")
  private List<Dibs> dibsList;
}
