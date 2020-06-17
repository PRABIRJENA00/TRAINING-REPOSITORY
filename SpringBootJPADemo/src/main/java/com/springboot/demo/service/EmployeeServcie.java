package com.springboot.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.demo.exception.ResourceNotFoundException;
import com.springboot.demo.model.Employee;
import com.springboot.demo.repository.EmployeeRepository;

@Service
public class EmployeeServcie {

	@Autowired
	EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployee() {

		List<Employee> employees = (List<Employee>) employeeRepository.getAllEmployee();

		if (employees.size() > 0) {
			return employees;
		} else
			return new ArrayList<Employee>();
	}

	public List<Employee> getAllEmployeesByExpDesc() throws ResourceNotFoundException {

		List<Employee> sortedEmployees = (List<Employee>) employeeRepository.getAllSortedEmployee();
		if (sortedEmployees.isEmpty()) {
			throw new ResourceNotFoundException("Resource Not Found");
		} else
			return sortedEmployees;
	}

	public List<Employee> getMoreExpEmployees() throws ResourceNotFoundException {

		List<Employee> expEmployees = (List<Employee>) employeeRepository.getMoreExpEmployees();
		if (expEmployees.isEmpty()) {
			throw new ResourceNotFoundException("Resource Not Found");
		} else
			return expEmployees;
	}

	public List<Employee> getLessExpEmployees() throws ResourceNotFoundException {

		List<Employee> lessExpEmployees = (List<Employee>) employeeRepository.getLessExpEmployees();
		if (lessExpEmployees.isEmpty()) {
			throw new ResourceNotFoundException("Resource Not Found");
		} else
			return lessExpEmployees;
	}

	public List<Employee> getEmployeesByPage(Integer pageNo, Integer pageSize, String sortBy)
			throws ResourceNotFoundException {

		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Employee> page = employeeRepository.findAll(pageable);
		if (page.hasContent()) {
			return page.getContent();
		} else {
			throw new ResourceNotFoundException("Employee Not found for this page");
		}
	}

	public Iterable<Employee> getAllEmployeeOrderByExp(String sortBy) throws Exception {
		List<Employee> employees;
		if(!sortBy.isEmpty()) {
		 employees = employeeRepository.findAll(Sort.by(sortBy));
		}else {
			throw new Exception("Internal Server Error");
		}
		if (employees.isEmpty()) {
			throw new ResourceNotFoundException("Resource Not Found");
		} else
			return employees;
	}

	public Optional<Employee> findById(int id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		Optional<Employee> employee = employeeRepository.findById(id);
		if (employee.isPresent()) {
			return employee;
		} else {
			throw new ResourceNotFoundException("Resource Not found");
		}
	}

	public Employee saveOrUpdate(Employee employee) throws ResourceNotFoundException {
		return employeeRepository.save(employee);
	}

	public void delete(Integer id) {
		employeeRepository.deleteById(id);
	}
}
