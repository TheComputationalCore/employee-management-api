package com.example.employee_management_api;

import com.example.employee_management_api.model.Employee;
import com.example.employee_management_api.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ActiveProfiles("test")
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll();
    }

    @Test
    void testSaveEmployee() {
        Employee emp = new Employee(null, "John Doe", "john@example.com", "IT");
        Employee saved = employeeRepository.save(emp);

        assertThat(saved.getEmployeeId()).isNotNull();
    }

    @Test
    void testFindAllEmployees() {
        employeeRepository.save(new Employee(null, "A", "a@example.com", "IT"));
        employeeRepository.save(new Employee(null, "B", "b@example.com", "HR"));

        List<Employee> list = employeeRepository.findAll();

        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    void testFindById() {
        Employee emp = employeeRepository.save(
                new Employee(null, "Sam", "sam@example.com", "IT")
        );

        Employee found = employeeRepository.findById(emp.getEmployeeId()).orElse(null);

        assertThat(found).isNotNull();
        assertThat(found.getEmployeeName()).isEqualTo("Sam");
    }
}
