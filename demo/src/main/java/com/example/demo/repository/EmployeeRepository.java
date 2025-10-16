package com.example.demo.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Employee;
import java.util.Optional;
import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
   // JpaRepository에 이미 정의된 findById와 동일 시그니처이지만
    // 명시적으로 선언하고 싶다면 아래와 같이 추가할 수 있습니다.
    Optional<Employee> findById(Integer empNo);

    // 엔티티 PK 이름(empNo)으로 찾는 편의 메서드도 추가
    Optional<Employee> findByEmpNo(Integer empNo);

    
    boolean existsById(Integer empNo);

    void deleteById(Integer empNo);

    // Specification을 이용한 검색용 시그니처 (JpaSpecificationExecutor#findAll(Specification) 사용 가능)
    List<Employee> findAll(Specification<Employee> spec);

    
}
