package com.librarymanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.librarymanagement.dao.UserDao;
import com.librarymanagement.pojo.Login;
import com.librarymanagement.pojo.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService implements IUserService {
	@Autowired
	private UserDao userDao;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(15); // Strength is 15
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtService  jwtService;
	
	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		user.setPassword(encoder.encode(user.getPassword()));
		return userDao.save(user);
	}
	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}
	@Override
	public String verify(Login login) {
		System.out.println("Username: " + login.getUserName());
	    System.out.println("Password: " + login.getPassword());
		try {
			Authentication authenticate = authManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUserName(), login.getPassword()));
			return jwtService.generateToken(login.getUserName());
		
		}catch(Exception e) {
			 System.out.println("Authentication failed for username: " + login.getUserName());
			throw new AuthenticationCredentialsNotFoundException("Invalid Crendentials "+ e.getMessage());
		}
	}
	
	
	


}
