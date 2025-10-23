package com.example.demo.domain;

import java.util.List;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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

    @OneToMany(mappedBy = "employee")
    private List<Book> books;

    // 추가 필드
    @Column(length = 200)
    private String email;

    @Column(length = 20)
    private String phoneNumber;

    @Temporal(TemporalType.DATE)
    @Column(name = "hire_date")
    private Date hireDate;

    @Column
    private Integer salary;
}
