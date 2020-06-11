package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.Student;
import com.demo.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	StudentRepository studentRepository;

	public List<Student> getAllStudent() {
		return (List<Student>) studentRepository.findAll();
	}

	public Student getStudentById(int id) {
		return studentRepository.findById(id).get();
	}

	public Student saveOrUpdate(Student student) {
		return studentRepository.save(student);
	}

	public void delete(int id) {
		studentRepository.deleteById(id);
	}
}