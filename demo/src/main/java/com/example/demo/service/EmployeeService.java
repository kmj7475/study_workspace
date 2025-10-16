package com.example.demo.service;

import com.example.demo.domain.Employee;

import java.util.List;
import java.util.Optional;

/**
 * 직원 관련 비즈니스 로직 인터페이스
 */
public interface EmployeeService {

    /**
     * 직원 등록
     */
    Employee createEmployee(Employee employee);

    /**
     * 직원 수정
     */
    Employee updateEmployee(Employee employee);

    /**
     * 사원번호로 직원 삭제
     */
    void removeEmployeeById(Integer empNo);

    /**
     * 사원번호로 단건 조회
     */
    Optional<Employee> getEmployeeByEmpNo(Integer empNo);

    /**
     * 다중조건 검색 (예: probe 객체의 필드가 비어있지 않은 조건으로 검색)
     */
    List<Employee> searchEmployees(Employee probe);

    /**
     * 전체 목록 조회
     */
    List<Employee> findAll();
}