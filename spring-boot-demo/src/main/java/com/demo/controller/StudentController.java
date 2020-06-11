package com.demo.controller;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.demo.model.Student;
import com.demo.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "StudentController", description = "API for Student Entity")
@RequestMapping("/students")
public class StudentController {
	@Autowired
	StudentService studentService;

	@ApiOperation(value = "View list of available api for student", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful Response"),
			@ApiResponse(code = 201, message = "Created Successfully"),
			@ApiResponse(code = 404, message = "Resource Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })

	@GetMapping
	private ResponseEntity<List<Student>> getAllStudent() {
		return ResponseEntity.ok().body(studentService.getAllStudent());
	}

	@ApiOperation(value = "Search a student with an ID", response = Student.class)
	@GetMapping("/{id}")
	private ResponseEntity<Student> getStudent(@PathVariable("id") int id) {
		return ResponseEntity.ok().body(studentService.getStudentById(id));
	}

	@ApiOperation(value = "Delete a student with an ID", response = Student.class)
	@DeleteMapping("/{id}")
	private ResponseEntity<Student> deleteStudent(@PathVariable("id") int id) {
		studentService.delete(id);
		return new ResponseEntity<Student>(HttpStatus.OK);
	}

	@ApiOperation(value = "Add a student", response = Student.class)
	@PostMapping
	private ResponseEntity<Student> saveStudent(@RequestBody Student student) {
		student = studentService.saveOrUpdate(student);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(student.getId())
				.toUri();
		return ResponseEntity.created(uri).body(student);
	}

}
