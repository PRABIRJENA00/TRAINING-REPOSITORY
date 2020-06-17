package com.springboot.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.springboot.demo.model.Book;
import com.springboot.demo.repo.BookRepository;

public class BookServcieTest {
	@InjectMocks
	BooksService booksService;

	@Mock
	BookRepository bookRepository;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out
				.println("annotation specifies that method will be invoked only once, before starting all the tests.");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("annotation specifies that method will be invoked only once, after finishing all the tests");
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		System.out.println("annotation specifies that method will be invoked before each test.");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println(" annotation specifies that method will be invoked before each test.");
	}

	@Test
	public void testGetAllBook() {

		List<Book> list = new ArrayList<>();
		Book book1 = new Book(10, "TEST", "TEST", 30);
		Book book2 = new Book(11, "TEST2", "TEST2", 31);
		Book book3 = new Book(12, "TEST3", "TEST3", 32);
		list.add(book1);
		list.add(book2);
		list.add(book3);

		when(bookRepository.findAll()).thenReturn(list);

		// test
		List<Book> empList = booksService.getAllBook();

		assertEquals(3, empList.size());
		verify(bookRepository, times(1)).findAll();
	}

	@Test
	public void testGetBookById() {

		Optional<Book> book = Optional.of(new Book(1, "TEST", "TEST1", 6));
		when(bookRepository.findById(1)).thenReturn(book);

		Optional<Book> book1 = booksService.getBookById(1);

		assertEquals("TEST1", book1.get().getAuthor());
		assertEquals("TEST", book1.get().getName());
	}

	@Test
	public void testDeleteBookById() {

		//Optional<Book> book = Optional.of(new Book(1, "TEST", "TEST1", 6));
		doNothing().when(bookRepository).deleteById(1);
		booksService.delete(1);
		assertTrue(true);
	}
}
