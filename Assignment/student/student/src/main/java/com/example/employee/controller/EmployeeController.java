package com.example.employee.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.employee.dto.EmployeeDto;
import com.example.employee.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

	@PostMapping()
	public ResponseEntity<Boolean> saveEmployee(@RequestBody EmployeeDto employeeDto) {
		employeeService.saveEmployee(employeeDto);
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	}

	@PutMapping()
	public ResponseEntity<Boolean> updateEmployee(@RequestBody EmployeeDto employeeDto) {
		employeeService.updateEmployee(employeeDto);
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getById(@PathVariable Long id) {
		EmployeeDto employeeDto = employeeService.getEmployeeById(id);
		return new ResponseEntity<>(employeeDto, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<List<EmployeeDto>> fetchAllEmployees() {
		List<EmployeeDto> employees = employeeService.fetchAllEmployees();
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

}
