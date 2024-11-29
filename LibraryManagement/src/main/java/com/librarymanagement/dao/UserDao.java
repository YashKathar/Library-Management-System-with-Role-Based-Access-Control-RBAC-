package com.librarymanagement.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.librarymanagement.pojo.User;
import java.util.List;


@Repository
public interface UserDao extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String Email);  
	User findByUsername(String username);
}
