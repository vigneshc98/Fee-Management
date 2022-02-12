package com.vtech.feemanagement.service;

import java.util.List;

import com.vtech.feemanagement.entity.AppUser;
import com.vtech.feemanagement.entity.Authority;

public interface AppUserService {
	
	void saveUser(AppUser appUser);
	
	Authority saveAuth(Authority athority);

	List<AppUser> getUsers();
	
	List<AppUser> getUser(String username);
	
	AppUser update(AppUser appUser);
	
	void delete(int id);
	
	void addRoleToUser(String username, String role);
	
}
