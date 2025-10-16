package com.example.demo.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Employee;
import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // Specification을 이용한 검색용 시그니처 (JpaSpecificationExecutor#findAll(Specification) 사용 가능)
    List<Employee> findAll(Specification<Employee> spec);

    
}
