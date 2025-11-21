package com.example.employee_api.controller;

import com.example.employee_api.model.Employee;
import com.example.employee_api.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Tag(name = "Employee Controller", description = "Manages employee data")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    @Operation(summary = "Show form")
    public String showForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "index";
    }

    @PostMapping("/saveEmployee")
    @Operation(summary = "Save employee")
    public String saveEmployee(@ModelAttribute Employee employee, Model model) {
        employeeService.saveEmployee(employee);
        model.addAttribute("message", "Employee saved successfully!");
        model.addAttribute("employee", new Employee());
        return "index";
    }

    @GetMapping("/displayAll")
    @Operation(summary = "Display all employees")
    public String displayAll(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "displayAll";
    }

    @GetMapping("/display/{id}")
    @Operation(summary = "Display employee by ID")
    public String displayById(@PathVariable String id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "display";
    }
}
