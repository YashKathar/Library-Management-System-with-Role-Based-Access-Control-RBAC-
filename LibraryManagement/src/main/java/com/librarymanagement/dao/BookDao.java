package com.librarymanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.librarymanagement.pojo.Book;

public interface BookDao extends JpaRepository<Book, Long> {

}
