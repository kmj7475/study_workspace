package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Dibs {
    @Id
    @Column(length = 6, nullable = false)
    private int memberNo;
    @Column(length = 10, nullable = false)
    private int bookNo;
}
