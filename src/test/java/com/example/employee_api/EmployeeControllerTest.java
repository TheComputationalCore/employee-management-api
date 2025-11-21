package com.example.employee_api.controller;

import com.example.employee_api.model.Employee;
import com.example.employee_api.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void testShowForm() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void testDisplayAll() throws Exception {
        when(employeeService.getAllEmployees()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/displayAll"))
                .andExpect(status().isOk())
                .andExpect(view().name("displayAll"));
    }

    @Test
    void testSaveEmployee() throws Exception {
        when(employeeService.saveEmployee(any(Employee.class)))
                .thenReturn(new Employee("101", "Dinesh", "d@example.com", "India"));

        mockMvc.perform(post("/saveEmployee")
                .param("employeeId", "101")
                .param("employeeName", "Dinesh")
                .param("employeeEmail", "d@example.com")
                .param("location", "India"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void testDisplayById() throws Exception {
        when(employeeService.getEmployeeById("101"))
                .thenReturn(new Employee("101", "Dinesh", "d@example.com", "India"));

        mockMvc.perform(get("/display/101"))
                .andExpect(status().isOk())
                .andExpect(view().name("display"));
    }
}
