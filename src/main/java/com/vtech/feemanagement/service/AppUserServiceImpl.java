package com.vtech.feemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vtech.feemanagement.entity.AppUser;
import com.vtech.feemanagement.entity.Authority;
import com.vtech.feemanagement.repository.AppUserRepository;

@Service
public class AppUserServiceImpl implements AppUserService {
	
	@Autowired
	private AppUserRepository appUserRepository;

	@Override
	public void saveUser(AppUser appUser) {
		// TODO Auto-generated method stub

	}

	@Override
	public Authority saveAuth(Authority athority) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppUser> getUsers() {
		// TODO Auto-generated method stub
		return appUserRepository.findAll();
	}

	@Override
	public List<AppUser> getUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppUser update(AppUser appUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addRoleToUser(String username, String role) {
		// TODO Auto-generated method stub

	}

}
