package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="member_no", length = 6, nullable = false)
    private Long memberNo;
    @Column(length = 10, nullable = false)
    private String memberId;
    @Column(length = 50, nullable = false)
    private String memberName;
    @Column(length = 50, nullable = false)
    private String password;
    @Column(length = 50, nullable = false)
    private String email;
    @Column(length = 20, nullable = false)
    private String phone;
    @Column(length = 100, nullable = false)
    private String likes;
    @Column(length = 1, nullable = false)
    private String sms;
    @Column(length = 10, nullable = false)
    private long point;
}
