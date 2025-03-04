package com.librarymanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarymanagement.dao.BookDao;
import com.librarymanagement.pojo.Book;

@Service
public class BookService implements IBookService {
	@Autowired
	private BookDao bookDao;
	
	@Override
	public Book addBook(Book book) {
		// TODO Auto-generated method stub
		return bookDao.save(book);
	}

	@Override
	public Book getBook(Long bookId) {
		// TODO Auto-generated method stub
		return bookDao.findById(bookId).get();
	}

	@Override
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		return bookDao.findAll();
	}

	@Override
	public Book deleteBook(Long bookId) {
		Book book = bookDao.findById(bookId).get();
		if(book != null) {
			bookDao.deleteById(bookId);
			return book;
		}
		return null;
	}

}
