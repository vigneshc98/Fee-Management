package com.vtech.feemanagement.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.vtech.feemanagement.filters.JwtAuthenticationFilter;
import com.vtech.feemanagement.filters.RestAuthenticationEntryPoint;
import com.vtech.feemanagement.security.service.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
	private RestAuthenticationEntryPoint entryPoint;
	
	@Autowired
	private JwtAuthenticationFilter authFilter;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.exceptionHandling().authenticationEntryPoint(entryPoint);
		
		http.cors();
		http.authorizeRequests().antMatchers("/api/auth/login","/api/auth/refresh","/images/**").permitAll();
		http.authorizeRequests().antMatchers("/api/students/**","/api/studentsrest/**", "/api/auth/**").hasAnyAuthority("ADMIN","SUPER_ADMIN");
		http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
//		http.formLogin();
//		http.authorizeRequests().anyRequest().authenticated();
		http.csrf().disable();
	}
	
}
