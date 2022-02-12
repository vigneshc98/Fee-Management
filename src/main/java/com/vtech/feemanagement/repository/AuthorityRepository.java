package com.vtech.feemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.vtech.feemanagement.entity.Authority;

@CrossOrigin
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
