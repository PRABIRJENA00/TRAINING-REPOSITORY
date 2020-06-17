package com.springboot.demo.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.springboot.demo.exception.ResourceNotFoundException;
import com.springboot.demo.model.Employee;
import com.springboot.demo.service.EmployeeServcie;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "EmployeeController", description = "API for Employee Entity")
@RequestMapping("/employees")
public class EmployeeController {

	private static final Logger logger=LoggerFactory.getLogger(EmployeeController.class);
	@Autowired
	EmployeeServcie employeeServcie;

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful Response"),
			@ApiResponse(code = 201, message = "Created Successfully"),
			@ApiResponse(code = 404, message = "Resource Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@ApiOperation(value = "Get All the Employees", response = Employee.class)
	@GetMapping
	private ResponseEntity<List<Employee>> getAllEmployee() {
		logger.info("Get all the employee details");
		return ResponseEntity.ok().body(employeeServcie.getAllEmployee());
		
	}

	@ApiOperation(value = "Get All Employees Sorted By ID", response = Employee.class)
	@GetMapping("/getAllSortedEmployees")
	private ResponseEntity<List<Employee>> getAllSortedEmployee() throws ResourceNotFoundException {
		logger.info("Get All Employees Sorted By ID");
		return ResponseEntity.ok().body(employeeServcie.getAllEmployeesByExpDesc());
	}

	@ApiOperation(value = "Get Employees More Than 5 Years Exp", response = Employee.class)
	@GetMapping("/getMoreExpEmployees")
	private ResponseEntity<List<Employee>> getMoreExpEmployees() throws ResourceNotFoundException {
		logger.info("Get Employees More Than 5 Years Exp");
		return ResponseEntity.ok().body(employeeServcie.getMoreExpEmployees());
	}

	@ApiOperation(value = "Get Employees Between 0 to 5 Years Exp", response = Employee.class)
	@GetMapping("/getLessExpEmployees")
	private ResponseEntity<List<Employee>> getLessExpEmployees() throws ResourceNotFoundException {
		logger.info("Get Employees Between 0 to 5 Years Exp");
		return ResponseEntity.ok().body(employeeServcie.getLessExpEmployees());
	}

	@ApiOperation(value = "Get Employees By PageNo With ASC Order Of Exp", response = Employee.class)
	@GetMapping("/getEmployeesByPage")
	private ResponseEntity<List<Employee>> getEmployeesByPage(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "0") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy)
			throws ResourceNotFoundException {

		List<Employee> list = employeeServcie.getEmployeesByPage(pageNo, pageSize, sortBy);

		return new ResponseEntity<List<Employee>>(list, new HttpHeaders(), HttpStatus.OK);

	}

	@ApiOperation(value = "Get All Employees With ASC Order By Exp", response = Employee.class)
	@GetMapping("/sortBy")
	public ResponseEntity<Iterable<Employee>> getAllEmployeeOrderByExp(@RequestParam(defaultValue = "id") String sortBy) throws Exception {
		return ResponseEntity.ok().body(employeeServcie.getAllEmployeeOrderByExp(sortBy));
	}

	@ApiOperation(value = "Search a Employee With an ID", response = Employee.class)
	@GetMapping("/getEmployeeById")
	private ResponseEntity<Optional<Employee>> getEmployeeById(@RequestParam Integer id)
			throws ResourceNotFoundException {
		
		String response="Hi"+id +"Welcome";
		logger.debug("Response {}",response);
		logger.info("Search a employee by id");
		logger.warn("Employee id should not be null");

		
		return ResponseEntity.ok().body(employeeServcie.findById(id));
	}

	@ApiOperation(value = "Delete a Employee With an ID", response = Employee.class)
	@DeleteMapping
	private ResponseEntity<Employee> deleteEmployeeById(@RequestParam(defaultValue = "0") Integer id) {
		employeeServcie.delete(id);
		return new ResponseEntity<Employee>(HttpStatus.OK);
	}

	@ApiOperation(value = "Add a Employee", response = Employee.class)
	@PostMapping
	private ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee)
			throws ResourceNotFoundException {
		employee = employeeServcie.saveOrUpdate(employee);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(employee.getId())
				.toUri();
		return ResponseEntity.created(uri).body(employee);
	}

}
