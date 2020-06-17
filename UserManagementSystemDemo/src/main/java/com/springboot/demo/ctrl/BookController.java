package com.springboot.demo.ctrl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.demo.model.AuditUser;
import com.springboot.demo.model.Book;
import com.springboot.demo.service.AuditUserService;
import com.springboot.demo.service.BooksService;
import com.springboot.demo.service.UsersService;

@RestController
public class BookController {

	private static final Logger logger = LoggerFactory.getLogger(BookController.class);

	@Autowired
	private BooksService booksService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private AuditUserService auditUserService;

	// Find
	@GetMapping("/books")
	List<Book> findAll() {
		List<Book> list=null;
		logger.info("findAll() executed ");
		if (usersService.getAllUsers().get(0).getUserrole().contains("ADMIN")) {
			LocalDateTime now = LocalDateTime.now();
			LocalDateTime now1 = now.plusDays(1);
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String Date1 = now.format(format);
			String Date2 = now1.format(format);
			AuditUser auditUser = new AuditUser();
			// auditUser.setId();
			auditUser.setUsername("ADMIN");
			auditUser.setStartDate(Date1);
			auditUser.setEndDate(Date2);

			auditUserService.saveOrUpdate(auditUser);
			logger.info("Admin Data Added to the audit table");
			list = booksService.getAllBook();
		}
		return list;
	}

	// Save
	@PostMapping("/books")
	@ResponseStatus(HttpStatus.CREATED)
	public Book newBook(@Valid @RequestBody Book newBook) {
		logger.info("newBook() execution start");
		Book book=null;
		if (usersService.getAllUsers().get(0).getUserrole().contains("ADMIN")) {
			LocalDateTime now = LocalDateTime.now();
			LocalDateTime now1 = now.plusDays(1);
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String Date1 = now.format(format);
			String Date2 = now1.format(format);
			AuditUser auditUser = new AuditUser();
			// auditUser.setId();
			auditUser.setUsername("ADMIN");
			auditUser.setStartDate(Date1);
			auditUser.setEndDate(Date2);
			logger.info("Admin Data Added to the audit table");
			auditUserService.saveOrUpdate(auditUser);
			book=booksService.saveOrUpdate(newBook);
		}
		logger.info("newBook() execution end");
		return book;
	}

	// Find
	@GetMapping("/books/{id}")
	Optional<Book> findOne(@PathVariable Integer id) {
		logger.info("findOne() execution start");
		Optional<Book> book = null;
		if (usersService.getAllUsers().get(0).getUserrole().contains("ADMIN")) {
			LocalDateTime now = LocalDateTime.now();
			LocalDateTime now1 = now.plusDays(1);
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String Date1 = now.format(format);
			String Date2 = now1.format(format);
			AuditUser auditUser = new AuditUser();
			// auditUser.setId();
			auditUser.setUsername("ADMIN");
			auditUser.setStartDate(Date1);
			auditUser.setEndDate(Date2);
			logger.info("Admin Data Added to the audit table");
			auditUserService.saveOrUpdate(auditUser);
			 book=booksService.getBookById(id);
		}
		return book;
	}

	// Save or update
	@PutMapping("/books/{id}")
	Book saveOrUpdate(@RequestBody Book newBook, @PathVariable Integer id) {
		logger.info("saveOrUpdate() execution start");
		return booksService.saveOrUpdate(newBook);

	}

	@DeleteMapping("/books/{id}")
	void deleteBook(@PathVariable Integer id) {
		logger.info("deleteBook() execution start");
		booksService.delete(id);
	}

}
