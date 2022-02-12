package com.vtech.feemanagement.service;

import java.util.List;

import com.vtech.feemanagement.entity.Student;

public interface StudentService {
	
	void save(Student student);
	
	Student getStudentById(int id);
	
	List<Student> getStudents();
	
	List<Student> getStudent(String usn);
	
	Student update(Student student, int id);
	
	void delete(int id);


}
