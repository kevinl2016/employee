package com.example.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Transactional
	@PostConstruct
	public void init() {
		employeeRepository.deleteAllInBatch();
		Employee emp1= new Employee(100, "Alan");
		Employee emp2= new Employee(220, "Martin");
		Employee emp3= new Employee(150, "Jamie");
		Employee emp4= new Employee(275, "Alex");
		Employee emp5= new Employee(400, "Steve");
		Employee emp6= new Employee(190, "David");
		emp1.setManager(emp3);
		emp2.setManager(emp1);
		emp4.setManager(emp1);
		emp5.setManager(emp3);
		emp6.setManager(emp5);
		employeeRepository.save(emp1);
		employeeRepository.save(emp2);
		employeeRepository.save(emp3);
		employeeRepository.save(emp4);
		employeeRepository.save(emp5);
		employeeRepository.save(emp6);
	}
	
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

}
