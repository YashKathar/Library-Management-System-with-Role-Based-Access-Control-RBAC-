package com.librarymanagement.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.librarymanagement.pojo.Login;
import com.librarymanagement.pojo.User;

public interface IUserService {

	User addUser(User user);
	List<User> getAllUsers();
	String verify(Login login);
	
}
