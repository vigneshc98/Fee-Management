package com.vtech.feemanagement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
//import com.vtech.feemanagement.repository.StudentRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.vtech.feemanagement.entity.AppUser;
import com.vtech.feemanagement.entity.Authority;
import com.vtech.feemanagement.entity.Student;
import com.vtech.feemanagement.repository.AppUserRepository;
import com.vtech.feemanagement.repository.StudentRepository;

@SpringBootApplication
public class Application implements RepositoryRestConfigurer {
	
//	@Autowired
//	private StudentRepository studentRepository;
	
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	
//	@Autowired
//	private AppUserRepository appUserRepository;
//
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(Student.class);
    } 
//	@PostConstruct
//	protected void init() {
//
//		Student student = new Student();
//		student.setName("Bharath");
//		student.setUsn("ISE003");
//		student.setSem("8");
//		student.setDepartment("ISE");
//		student.setActivityfee(0);
//		student.setAlumnifee(0);
//		student.setBusfee(0);
//		student.setCollegefee(0);
//		student.setEsdpfee(0);
//		studentRepository.save(student);
//	}
	
//	@PostConstruct
//	protected void init() {
//		List<Authority> authorityList = new ArrayList<>();
//		authorityList.add(createAuthority("USER"));
//		authorityList.add(createAuthority("ADMIN"));
//		
//		AppUser user = new AppUser();
//		user.setUserName("vigneshc16");
//		user.setPassword(passwordEncoder.encode("test1234"));
//		user.setCreatedAt(new Date());
//		user.setUpdatedAt(new Date());
//		user.setFirstName("Vignesh");
//		user.setLastName("c");
//		user.setEmail("vignesh@gmail.com");
//		user.setPhoneNumber("9110857273");
//		user.setEnabled(true);
//		
//		user.setAuthorities(authorityList);
//		
//		appUserRepository.save(user);
//		
//	}
//
//	private Authority createAuthority(String roleCode) {
//		Authority authority = new Authority();
//		authority.setRoleCode(roleCode);
//		return authority;
//	}
	

}
