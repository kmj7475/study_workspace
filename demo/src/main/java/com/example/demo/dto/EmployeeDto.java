package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Date;

/**
 * 직원 전송 객체 (DTO)
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Integer empNo;
    private String empName;

    /**
     * 담당 도서 목록(필요 시 BookDto 또는 Book 엔티티로 교체)
     * 현재는 도서 PK 리스트로 간단히 표현
     */
    private List<Integer> bookNos;

    // 추가된 필드
    private String email;
    private String phoneNumber;
    private Date hireDate;
    private Integer salary;
}