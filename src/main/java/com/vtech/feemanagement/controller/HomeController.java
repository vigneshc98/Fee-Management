package com.vtech.feemanagement.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vtech.feemanagement.entity.Student;
import com.vtech.feemanagement.service.StudentService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class HomeController {
	
	@Autowired
	private StudentService studentServiceImpl;

	@GetMapping("/home")
	public ResponseEntity<?> home(){
		return ResponseEntity.ok("Welcome Hello world");
	}
	
	@GetMapping("/students")
	public ResponseEntity<List<Student>> searchAll() {
		return ResponseEntity.ok(studentServiceImpl.getStudents());
	}
	
	@GetMapping("/students/{usn}")
	public ResponseEntity<List<Student>> searchStudent(@PathVariable String usn) {
		return ResponseEntity.ok(studentServiceImpl.getStudent(usn));
	}
	
	@PostMapping("/students")
	public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
		student.setId(0);
		studentServiceImpl.save(student);
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/student").toUriString());
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/students/{id}")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable int id) {
		studentServiceImpl.update(student, id);
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/student/id").toUriString());
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/students/{id}")
	public ResponseEntity<Student> delete(@PathVariable int id){
		studentServiceImpl.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/students/get/{id}")
	public ResponseEntity<Student> searchStudentById(@PathVariable int id) {
		return ResponseEntity.ok(studentServiceImpl.getStudentById(id));
	}
	
	
	
	
	
}
