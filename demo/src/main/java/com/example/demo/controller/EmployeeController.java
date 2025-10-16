package com.example.demo.controller;

import com.example.demo.domain.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * 전체조회 / 다중조건 검색
     */
    @GetMapping
    public String list(@ModelAttribute("employee") Employee probe, Model model) {
        List<Employee> employees = employeeService.searchEmployees(probe);
        model.addAttribute("employees", employees);
        // probe는 검색 폼용으로 뷰에 필요
        return "employee_list";
    }

    /**
     * 신규 등록 폼
     */
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee_form";
    }

    /**
     * 단건조회(조회 또는 폼에 데이터 바인딩)
     */
    @GetMapping("/{id}")
    public String view(@PathVariable("id") Integer empNo, Model model, RedirectAttributes ra) {
        Optional<Employee> opt = employeeService.getEmployeeByEmpNo(empNo);
        if (opt.isPresent()) {
            model.addAttribute("employee", opt.get());
            return "employee_form";
        } else {
            ra.addFlashAttribute("errorMessage", "해당 직원이 존재하지 않습니다: " + empNo);
            return "redirect:/employees";
        }
    }

    /**
     * 편집 폼(별도 경로)
     */
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Integer empNo, Model model, RedirectAttributes ra) {
        return view(empNo, model, ra);
    }

    /**
     * 등록 처리
     */
    @PostMapping("/save")
    public String save(@ModelAttribute Employee employee, RedirectAttributes ra) {
        Employee saved = employeeService.createEmployee(employee);
        ra.addFlashAttribute("successMessage", "등록되었습니다: " + saved.getEmpNo());
        return "redirect:/employees";
    }

    /**
     * 수정 처리
     */
    @PostMapping("/update")
    public String update(@ModelAttribute Employee employee, RedirectAttributes ra) {
        Employee updated = employeeService.updateEmployee(employee);
        ra.addFlashAttribute("successMessage", "수정되었습니다: " + updated.getEmpNo());
        return "redirect:/employees";
    }

    /**
     * 삭제 처리 (템플릿에서 GET 링크로 호출하는 구조를 그대로 사용)
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer empNo, RedirectAttributes ra) {
        employeeService.removeEmployeeById(empNo);
        ra.addFlashAttribute("successMessage", "삭제되었습니다: " + empNo);
        return "redirect:/employees";
    }
}