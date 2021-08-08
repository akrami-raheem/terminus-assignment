package com.example.employee.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.employee.dto.EmployeeDto;
import com.example.employee.model.EmployeeEntity;
import com.example.employee.service.EmployeeDataMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeDataMapperImpl implements EmployeeDataMapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDataMapperImpl.class);

	@Override
	public EmployeeEntity mapToEmployeeEntity(EmployeeDto employeeDto) {
		LOGGER.info("Mapping Employee DTO to Employee Entity");
		LOGGER.info("Input Dto {} ", employeeDto);
		EmployeeEntity employeeEntity = EmployeeEntity.builder().firstName(employeeDto.getFirstName())
				.lastName(employeeDto.getLastName())
				.emailId(employeeDto.getEmailId())
				.id(employeeDto.getId())
				.build();
		LOGGER.info("Mapped Entity {} ", employeeEntity);
		return employeeEntity;
	}

	@Override
	public List<EmployeeDto> mapToEmployeeDto(List<EmployeeEntity> entities) {
		LOGGER.info("Mapping Employee entities to Employee Dto");
		List<EmployeeDto> employeeDtos = entities.stream()
				.map(p -> EmployeeDto.builder().id(p.getId()).firstName(p.getFirstName()).lastName(p.getLastName())
						.emailId(p.getEmailId()).build())
				.collect(Collectors.toList());
		return employeeDtos;
	}

	@Override
	public EmployeeDto mapToEmployeeDto(EmployeeEntity entity) {
		LOGGER.info("Mapping Employee entities to Employee Dto");
		EmployeeDto employeeDtos = EmployeeDto.builder().id(entity.getId()).firstName(entity.getFirstName())
				.lastName(entity.getLastName()).emailId(entity.getEmailId())
				.build();

		return employeeDtos;
	}

}
