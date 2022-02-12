package com.vtech.feemanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.vtech.feemanagement.entity.AppUser;

@CrossOrigin
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

	Optional<AppUser> findByUserName(String userName);
}
