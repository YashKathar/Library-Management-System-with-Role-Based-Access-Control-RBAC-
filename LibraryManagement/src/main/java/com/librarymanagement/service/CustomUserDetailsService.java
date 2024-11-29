package com.librarymanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.librarymanagement.dao.UserDao;
import com.librarymanagement.pojo.CustomUserDetails;
import com.librarymanagement.pojo.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user =  userDao.findByUsername(username);
		System.out.println(username);
		
		if(user == null) {
			System.out.println("User is not found");
			throw new UsernameNotFoundException("User Not found");
		}
		
		return new CustomUserDetails(user);
	}

}
