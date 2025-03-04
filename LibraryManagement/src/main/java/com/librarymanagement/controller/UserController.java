package com.librarymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.librarymanagement.pojo.Login;
import com.librarymanagement.pojo.User;
import com.librarymanagement.service.IUserService;

@RestController
@RequestMapping("api/user")
public class UserController {
	@Autowired
	private IUserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody User user){
		User usr = userService.addUser(user);
		return new ResponseEntity<>(usr, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody Login login){
		 String verify = userService.verify(login);
		 return verify;
	}
}
