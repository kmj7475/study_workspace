package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "dibs")
public class Dibs {
    @Id
    @Column(length = 6, nullable = false)
    private int memberNo;

    @ManyToOne
    @JoinColumn(name = "book_no", insertable = false, updatable = false)
    private Book book;
}
