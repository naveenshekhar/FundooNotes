package com.example.elasticsearch.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.elasticsearch.dao.BookDao;
import com.example.elasticsearch.model.Book;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookDao bookDao;

	public BookController(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	@GetMapping("/{id}")
	public Map<String, Object> getBookById(@PathVariable String id) {
		return bookDao.getBookById(id);
	}

	@PostMapping
	public Book insertBook(@RequestBody Book book) throws Exception {
		return bookDao.insertBook(book);
	}

	@PutMapping("/{id}")
	public Map<String, Object> updateBookById(@RequestBody Book book, @PathVariable String id) {
		return bookDao.updateBookById(id, book);
	}

	@DeleteMapping("/{id}")
	public void deleteBookById(@PathVariable String id) {
		bookDao.deleteBookById(id);
	}

}
