package com.vtech.feemanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.vtech.feemanagement.entity.Student;

@CrossOrigin
@RepositoryRestResource(path="studentsrest", collectionResourceRel = "studentList")
public interface StudentRepository extends JpaRepository<Student, Integer> {

	List<Student> findByUsn(String usn);
	

}
