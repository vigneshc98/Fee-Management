package com.vtech.feemanagement.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vtech.feemanagement.entity.AppUser;
import com.vtech.feemanagement.reqres.LoginRequest;
import com.vtech.feemanagement.reqres.LoginResponse;
import com.vtech.feemanagement.security.utils.JwtTokenUtil;
import com.vtech.feemanagement.service.AppUserService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class AuthenticationController {
		
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AppUserService appUserService;
	
	@PostMapping("/auth/login")
	public ResponseEntity<?> userLogin(@RequestBody LoginRequest loginRequest ){
		
		Authentication authentication=null;
		
		try {
			 authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		}catch(Exception exc) {
			exc.printStackTrace();
		}
				
//		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		AppUser user = (AppUser) authentication.getPrincipal();
		
		String accessToken=null;
		String refreshToken=null;
		
		try {
			accessToken = jwtTokenUtil.generateAccessToken(user);		
			refreshToken = jwtTokenUtil.generateRefreshToken(user);
			
		} catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		System.out.println("Token:"+accessToken);
		
		LoginResponse response = new LoginResponse();
		response.setAccessToken(accessToken);
		response.setRefreshToken(refreshToken);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/auth/refresh")
	public ResponseEntity<?> getRefresh(HttpServletRequest request) throws IOException{
		
		String refresh_token = jwtTokenUtil.getToken(request);
		
		String accessToken=null;
		
		if(refresh_token!=null) {

			
			String username = jwtTokenUtil.getUsernameFromToken(refresh_token);
			
			if(username!=null) {
				AppUser user = (AppUser) userDetailsService.loadUserByUsername(username);
				
				if(jwtTokenUtil.validateToken(refresh_token, user)) {
					
					try {
						accessToken = jwtTokenUtil.generateAccessToken(user);	
						LoginResponse loginRes = new LoginResponse();
						loginRes.setAccessToken(accessToken);
						loginRes.setRefreshToken(refresh_token);
						return ResponseEntity.ok(loginRes);
						
					} catch (InvalidKeySpecException | NoSuchAlgorithmException exc) {
						exc.printStackTrace();
					}

				}

			}
			
		}
		return null;

	}
	
	@GetMapping("/auth/users")
	public ResponseEntity<List<AppUser>> getAuthUsers(){
		
		return ResponseEntity.ok(appUserService.getUsers());
	}

}


