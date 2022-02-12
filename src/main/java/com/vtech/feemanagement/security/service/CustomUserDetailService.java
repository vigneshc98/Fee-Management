package com.vtech.feemanagement.security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vtech.feemanagement.entity.AppUser;
import com.vtech.feemanagement.exception.ResourceNotFoundException;
import com.vtech.feemanagement.repository.AppUserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	private AppUserRepository appUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("<<<<<< CUSTOM USER DETAIL SERVICE >>>>>>>");
		
		AppUser appUser = appUserRepository.findByUserName(username).orElseThrow(() -> new ResourceNotFoundException("User not found with username:"+username));
		
		return appUser;
	}

}
