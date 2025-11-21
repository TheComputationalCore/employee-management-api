package com.example.employee_api.repository;

import com.example.employee_api.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll();
    }

    @Test
    void testSaveEmployee() {
        Employee employee = new Employee("101", "John", "john@example.com", "USA");
        employeeRepository.save(employee);

        Employee saved = employeeRepository.findById("101").orElse(null);

        assertThat(saved).isNotNull();
        assertThat(saved.getEmployeeName()).isEqualTo("John");
    }

    @Test
    void testFindAllEmployees() {
        employeeRepository.save(new Employee("1", "A", "a@gmail.com", "X"));
        employeeRepository.save(new Employee("2", "B", "b@gmail.com", "Y"));

        List<Employee> employees = employeeRepository.findAll();

        assertThat(employees).hasSize(2);
    }

    @Test
    void testFindById() {
        Employee employee = new Employee("5", "C", "c@gmail.com", "Z");
        employeeRepository.save(employee);

        Employee found = employeeRepository.findById("5").orElse(null);

        assertThat(found).isNotNull();
        assertThat(found.getEmployeeEmail()).isEqualTo("c@gmail.com");
    }
}
