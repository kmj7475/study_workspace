package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Dibs {
    @Column(length = 6, nullable = false)
    private int memberNo;
    @Column(length = 10, nullable = false)
    private int bookNo;
}
