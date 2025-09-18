package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "employees")
@Data
public class Employee {
    @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 6, nullable = false)
    private Integer empNo;

    @Column(length = 100)
    private String empName;
}
