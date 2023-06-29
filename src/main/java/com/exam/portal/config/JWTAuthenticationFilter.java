package com.exam.portal.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.exam.portal.service.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsServiceImpl detailsServiceImpl;

	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
	final String header = request.getHeader("Authorization");

	System.out.println(header);
	String userName=null;
	String token=null;
	if(header!=null && header.startsWith("Bearer ")) {
		try {
			token= header.substring(7);
			
			userName=this.jwtUtil.extractUsername(token);
		}catch (ExpiredJwtException e) {
			e.printStackTrace();
			System.err.println("token has expired");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}else {
		System.out.println("Token does not start with beared or not present");
	}
	
	if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
		final UserDetails userDetails = this.detailsServiceImpl.loadUserByUsername(userName);
	
		if(this.jwtUtil.validateToken(token, userDetails)) {
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
			
			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}
	
	}else {
		System.out.println("token not valid");
	}
	
	filterChain.doFilter(request, response);
	
	}

}
