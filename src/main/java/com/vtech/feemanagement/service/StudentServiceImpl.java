package com.vtech.feemanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vtech.feemanagement.entity.Student;
import com.vtech.feemanagement.exception.ResourceNotFoundException;
import com.vtech.feemanagement.repository.StudentRepository;
import com.vtech.feemanagement.reqres.LoginResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public void save(Student student) {
		studentRepository.save(student);
	}

	@Override
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	@Override
	public List<Student> getStudent(String usn) {
		List<Student> student = studentRepository.findByUsn(usn);
		
		return student;
	}

	@Override
	public Student update(Student student, int id) {
		
		Student resStudent = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
		student.setId(resStudent.getId());
		studentRepository.save(student);
		
		return  student;
	}

	@Override
	public void delete(int id) {
		Student resStudent = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
		studentRepository.deleteById(id);
	}

	@Override
	public Student getStudentById(int id) {
		return studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found"));

	}

}
