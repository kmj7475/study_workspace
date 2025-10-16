package com.example.demo.service.impl;

import com.example.demo.domain.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import com.example.demo.spec.EmployeeSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        // 기본 검증(필요시 확장)
        if (employee == null) {
            throw new IllegalArgumentException("employee must not be null");
        }
        // PK가 이미 존재하면 새로 생성하지 않도록 처리할 수 있음
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        if (employee == null || employee.getEmpNo() == null) {
            throw new IllegalArgumentException("employee or empNo must not be null");
        }
        Optional<Employee> opt = employeeRepository.findById(employee.getEmpNo());
        if (opt.isEmpty()) {
            throw new IllegalArgumentException("해당 직원이 존재하지 않습니다: " + employee.getEmpNo());
        }
        Employee existing = opt.get();
        // 필요한 필드만 복사(아이디 및 연관관계 관리 주의)
        BeanUtils.copyProperties(employee, existing, "empNo", "books");
        return employeeRepository.save(existing);
    }

    @Override
    public void removeEmployeeById(Integer empNo) {
        if (empNo == null) {
            throw new IllegalArgumentException("empNo must not be null");
        }
        if (employeeRepository.existsById(empNo)) {
            employeeRepository.deleteById(empNo);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Employee> getEmployeeByEmpNo(Integer empNo) {
        if (empNo == null) {
            return Optional.empty();
        }
        return employeeRepository.findById(empNo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> searchEmployees(Employee probe) {
        if (probe == null) {
            return employeeRepository.findAll();
        }
        Specification<Employee> spec = EmployeeSpecification.search(probe);
        return employeeRepository.findAll(spec);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
}