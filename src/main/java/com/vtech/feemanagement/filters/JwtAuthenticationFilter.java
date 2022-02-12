package com.vtech.feemanagement.filters;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vtech.feemanagement.entity.AppUser;
import com.vtech.feemanagement.security.utils.JwtTokenUtil;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		if(request.getServletPath().equals("/api/auth/login") || request.getServletPath().equals("/api/auth/refresh")) {
			filterChain.doFilter(request, response);
		}
		else {

			String accessToken = jwtTokenUtil.getToken(request);
					
			if(accessToken!=null) {
	
				String[] auths = null;
				
				String username = jwtTokenUtil.getUsernameFromToken(accessToken);
				
				try {
					auths = jwtTokenUtil.getAuthsFromToken(accessToken);
				}
				catch(Exception e) {
					response.sendError(HttpStatus.BAD_REQUEST.value());
				}
				
				if(username!=null && (auths!=null )) {
					
					AppUser userdetail = (AppUser) userDetailsService.loadUserByUsername(username);
					
					List<GrantedAuthority> authorities = new ArrayList<>();
					
					for(GrantedAuthority us : userdetail.getAuthorities()) {
						authorities.add(new SimpleGrantedAuthority(us.getAuthority()));
					}
					
					if(jwtTokenUtil.validateToken(accessToken, userdetail) && SecurityContextHolder.getContext().getAuthentication()==null ) {
						UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userdetail,null, authorities);
						usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetails(request));
						SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					}
	
				}
				
			}
			filterChain.doFilter(request, response);

	   }
		
    }
}

