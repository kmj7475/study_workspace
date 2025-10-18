package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

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

    // ✅ 양방향 매핑 (주문 목록)
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @JsonManagedReference
    private List<Order> orders = new ArrayList<>();
}
