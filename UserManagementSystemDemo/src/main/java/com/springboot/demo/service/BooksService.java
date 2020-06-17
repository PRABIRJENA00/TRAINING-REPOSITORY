package com.springboot.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.demo.model.Book;
import com.springboot.demo.repo.BookRepository;
@Service
public class BooksService {
	
	@Autowired
	BookRepository bookRepository;
	
	public List<Book> getAllBook() {
		return (List<Book>) bookRepository.findAll();
	}

	public Optional<Book> getBookById(int id) {
		return bookRepository.findById(id);
	}

	public Book saveOrUpdate(Book book) {
		return bookRepository.save(book);
	}

	public void delete(int id) {
		bookRepository.deleteById((int) id);
	}

}
