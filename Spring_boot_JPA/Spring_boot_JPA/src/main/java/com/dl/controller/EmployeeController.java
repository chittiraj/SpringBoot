package com.dl.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dl.model.Employee;
import com.dl.repository.EmployeeRepository;
import com.dl.service.EmployeeService;

@RestController
@RequestMapping("/api/emp")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/getEmployee")
	public ResponseEntity<List<Employee>> findAll(){
		List<Employee> employee = employeeService.findAll();
		return new ResponseEntity<List<Employee>>(employee, HttpStatus.OK);
	}
	@PostMapping("/save")
	public ResponseEntity<Employee> save(@RequestBody Employee employee){
		 Employee save = employeeService.save(employee);
		return new ResponseEntity<Employee>(save,HttpStatus.OK);
	}

}
