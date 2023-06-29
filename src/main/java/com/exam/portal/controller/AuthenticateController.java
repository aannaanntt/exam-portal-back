package com.exam.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.portal.config.JwtUtil;
import com.exam.portal.entity.JWTRequest;
import com.exam.portal.entity.JWTResponse;
import com.exam.portal.service.UserDetailsServiceImpl;

@RestController
public class AuthenticateController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private UserDetailsServiceImpl detailsServiceImpl;

	@Autowired
	private JwtUtil jwtUtil;
	
	
	
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JWTRequest jwtRequest) throws Exception{
		try {
			this.authenticate(jwtRequest.getUserName(), jwtRequest.getPassword());
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("User name not found");
		}
		
		UserDetails username = this.detailsServiceImpl.loadUserByUsername(jwtRequest.getUserName());
	
		String token = this.jwtUtil.generateToken(username);
		
		return ResponseEntity.ok(new JWTResponse(token));
	}
	

	private void authenticate(String userName, String password) throws Exception {
		try {

			authManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));

		} catch (DisabledException e) {
			throw new Exception("User disabled" + e.getMessage());
		} catch (BadCredentialsException e) {
			throw new Exception("Credentials not correct" + e.getMessage());
		}
	}
}
