package com.librarymanagement.filter;

import java.io.IOException;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.librarymanagement.service.CustomUserDetailsService;
import com.librarymanagement.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private CustomUserDetailsService custUserDetailsService;
	
	public JwtFilter() {
		System.out.println("In the OncePerRequestFiletr");
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String header = request.getHeader("Authorization");
		String token = null;
		String userName = null;
		
		if(header != null && header.startsWith("Bearer ")) {
			token = header.substring(7);
			userName = jwtService.extractUserName(token);
			
			if(userName != null && SecurityContextHolder.getContext().getAuthentication()==null) {
				UserDetails userDetails = custUserDetailsService.loadUserByUsername(userName);
				
				if(jwtService.validateToken(token, userDetails)) {
					UsernamePasswordAuthenticationToken userNamePasswordAuthToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					userNamePasswordAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(userNamePasswordAuthToken);
					
				}
			}
			filterChain.doFilter(request, response);
		}
		
	}

}
