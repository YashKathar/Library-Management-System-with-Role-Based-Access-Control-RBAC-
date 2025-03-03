package com.librarymanagement.service;

import java.util.List;

import com.librarymanagement.pojo.Book;

public interface IBookService {
	Book addBook(Book book);
	Book getBook(Long bookId);
	List<Book> getAllBooks();
	Book deleteBook(Long bookId);
}
