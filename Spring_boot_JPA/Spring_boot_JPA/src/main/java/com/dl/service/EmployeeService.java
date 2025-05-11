package com.dl.service;

import java.util.List;

import com.dl.model.Employee;

public interface EmployeeService {
	
	List<Employee> findAll();
	Employee save(Employee employee);

}
