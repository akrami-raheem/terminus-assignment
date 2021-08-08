package com.example.employee.service;

import java.util.List;

import com.example.employee.dto.EmployeeDto;

public interface EmployeeService {

    void saveEmployee(EmployeeDto studentDto);

    void updateEmployee(EmployeeDto studentDto);

    void deleteEmployee(Long id);

    List<EmployeeDto> fetchAllEmployees();

    EmployeeDto getEmployeeById(Long id);
}
