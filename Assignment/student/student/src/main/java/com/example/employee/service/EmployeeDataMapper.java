package com.example.employee.service;

import java.util.List;

import com.example.employee.dto.EmployeeDto;
import com.example.employee.model.EmployeeEntity;

public interface EmployeeDataMapper {

    EmployeeEntity mapToEmployeeEntity(EmployeeDto studentDto);

    List<EmployeeDto> mapToEmployeeDto(List<EmployeeEntity> entities);

    EmployeeDto mapToEmployeeDto(EmployeeEntity entity);
}
