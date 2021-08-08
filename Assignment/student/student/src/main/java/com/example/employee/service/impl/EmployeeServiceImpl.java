package com.example.employee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.employee.dto.EmployeeDto;
import com.example.employee.exception.EmployeeNotfoundException;
import com.example.employee.model.EmployeeEntity;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.service.EmployeeDataMapper;
import com.example.employee.service.EmployeeService;

import java.util.List;
import java.util.Optional;

@CacheConfig(cacheNames = { "employee" })
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDataMapper dataMapper;

	@Autowired
	private EmployeeRepository employeeRepository;

	@CacheEvict(value = "employee", key = "#employeeDto.id")
	@Override
	public void saveEmployee(EmployeeDto employeeDto) {
		EmployeeEntity entity = dataMapper.mapToEmployeeEntity(employeeDto);
		employeeRepository.save(entity);
	}

	@CachePut(value = "employee", key = "#employeeDto.id")
	@Override
	public void updateEmployee(EmployeeDto employeeDto) {
		Optional<EmployeeEntity> entity = employeeRepository.findById(employeeDto.getId());
		if (!entity.isPresent()) {
			throw new EmployeeNotfoundException("Employee not present for id {} : " + employeeDto.getId());
		}

		EmployeeEntity newEntity = dataMapper.mapToEmployeeEntity(employeeDto);
		newEntity.setId(entity.get().getId());
		employeeRepository.save(newEntity);
	}

	@CacheEvict(value = "employee", key = "#id")
	@Override
	public void deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
	}

	@Override
	public List<EmployeeDto> fetchAllEmployees() {
		List<EmployeeEntity> entities = employeeRepository.findAll();
		return dataMapper.mapToEmployeeDto(entities);
	}

	@Override
	@Cacheable(value = "employee", key = "#id")
	public EmployeeDto getEmployeeById(Long id) {
		Optional<EmployeeEntity> entity = employeeRepository.findById(id);
		if (!entity.isPresent()) {
			throw new EmployeeNotfoundException("Employee not present for id {} : " + id);
		}
		return dataMapper.mapToEmployeeDto(entity.get());
	}

}
