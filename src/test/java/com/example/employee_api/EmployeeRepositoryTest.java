package com.example.employee_api.repository;

import com.example.employee_api.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ActiveProfiles("test")   // REQUIRED
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setup() {
        employeeRepository.deleteAll();
    }

    @Test
    void testSaveEmployee() {
        Employee e = new Employee("1", "John", "john@example.com", "USA");
        employeeRepository.save(e);
        Employee saved = employeeRepository.findById("1").orElse(null);

        assertThat(saved).isNotNull();
        assertThat(saved.getEmployeeName()).isEqualTo("John");
    }

    @Test
    void testFindAllEmployees() {
        employeeRepository.save(new Employee("2", "Alex", "alex@example.com", "UK"));
        List<Employee> list = employeeRepository.findAll();

        assertThat(list).hasSize(1);
    }

    @Test
    void testFindById() {
        employeeRepository.save(new Employee("3", "Bob", "bob@example.com", "Canada"));
        Employee e = employeeRepository.findById("3").orElse(null);

        assertThat(e).isNotNull();
        assertThat(e.getEmployeeEmail()).isEqualTo("bob@example.com");
    }
}
