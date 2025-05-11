package com.dl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dl.model.Employee;
import com.dl.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRespository;
	
	@Override
	public List<Employee> findAll() {
		return employeeRespository.findAll();
	}

	@Override
	public Employee save(Employee employee) {
		return employeeRespository.save(employee);
	}

}
