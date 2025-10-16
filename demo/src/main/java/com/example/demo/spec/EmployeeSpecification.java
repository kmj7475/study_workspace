package com.example.demo.spec;

import com.example.demo.domain.Employee;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecification {

    public static Specification<Employee> search(Employee employee) {
        return (root, query, cb) -> {
            var predicate = cb.conjunction();
            if (employee == null) {
                return predicate;
            }

            if (employee.getEmpNo() != null) {
                predicate = cb.and(predicate, cb.equal(root.get("empNo"), employee.getEmpNo()));
            }

            if (employee.getEmpName() != null && !employee.getEmpName().isEmpty()) {
                predicate = cb.and(predicate, cb.like(root.get("empName"), "%" + employee.getEmpName() + "%"));
            }

            return predicate;
        };
    }
}