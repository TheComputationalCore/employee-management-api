package com.example.employee_api.repository;

import com.example.employee_api.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void testSaveEmployee() {
        Employee employee = new Employee("201", "Alice", "alice@example.com", "USA");
        Employee saved = employeeRepository.save(employee);

        assertNotNull(saved);
        assertEquals("Alice", saved.getEmployeeName());
    }

    @Test
    void testFindAllEmployees() {
        employeeRepository.save(new Employee("301", "Bob", "bob@example.com", "UK"));

        List<Employee> employees = employeeRepository.findAll();
        assertTrue(employees.size() >= 1);
    }

    @Test
    void testFindById() {
        Employee employee = new Employee("401", "Charlie", "charlie@example.com", "Canada");
        employeeRepository.save(employee);

        Employee found = employeeRepository.findById("401").orElse(null);

        assertNotNull(found);
        assertEquals("Charlie", found.getEmployeeName());
    }
}
