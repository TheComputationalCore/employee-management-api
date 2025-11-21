package com.example.employee_api.service;

import com.example.employee_api.exception.EmployeeNotFoundException;
import com.example.employee_api.model.Employee;
import com.example.employee_api.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employee = new Employee("101", "Dinesh", "dinesh@example.com", "India");
    }

    @Test
    void testSaveEmployee() {
        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee saved = employeeService.saveEmployee(employee);

        assertEquals("101", saved.getEmployeeId());
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void testGetEmployeeById_Found() {
        when(employeeRepository.findById("101")).thenReturn(Optional.of(employee));

        Employee found = employeeService.getEmployeeById("101");

        assertEquals("Dinesh", found.getEmployeeName());
    }

    @Test
    void testGetEmployeeById_NotFound() {
        when(employeeRepository.findById("999")).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployeeById("999"));
    }

    @Test
    void testGetAllEmployees() {
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee));

        assertEquals(1, employeeService.getAllEmployees().size());
    }
}
