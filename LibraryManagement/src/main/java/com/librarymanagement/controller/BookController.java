package com.librarymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.librarymanagement.pojo.Book;
import com.librarymanagement.service.IBookService;

import jakarta.validation.constraints.AssertFalse.List;

@RestController
@RequestMapping("api/book/")
public class BookController {
	
	@Autowired
	private IBookService bookService;
	
	@GetMapping("getallbooks")
	public java.util.List<Book> getAllBooks(){
		return bookService.getAllBooks();
	}
	
	@PostMapping("addbook")
	@PreAuthorize("hasRole('ADMIN')")
	public Book addBook(@RequestBody Book book) {
		return bookService.addBook(book); 
	}
	
	@GetMapping("getbookbyid/{BOOKID}")
	public Book getBookById(@PathVariable ("BOOKID") Long bookId) {
		return bookService.getBook(bookId);
		
	}
	
	@DeleteMapping("deletebook/{BOOKID}")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteBook(@PathVariable ("BOOKID") Long bookId) {
		Book deleteBook = bookService.deleteBook(bookId);
		if(deleteBook != null) {
			return "The book wih an Id = "+bookId+" and Title = "+deleteBook.getTitle()+" is deleted sucessfully";
		}else {
			return "Book with an Id = "+bookId+" was not found";
		}
	}
}
