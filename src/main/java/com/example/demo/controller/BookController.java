package com.example.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.BookDao;
import com.example.demo.model.Book;

/**
 * @author Achille
 *
 */
//@RestController
@Controller
@RequestMapping("/books")
public class BookController {

	private BookDao bookDao;

	// @Autowired
	public BookController(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	/**
	 * * find by title and prefix *
	 * ***************************************************
	 */

	@GetMapping(value = "/title/{title}", produces = "application/json; charset=utf-8")
	public String getTitle(@PathVariable String title, Model model) throws IOException {
		System.err.println("----------------------Title-----------------------" );

		List<Book> booksFound = bookDao.getBookByTitle(title);
		model.addAttribute("books", booksFound);

		return "booksPage";
	}

	/** * find all *************************************************** * */
	
	@GetMapping(value = "/findAllThyme", produces = "application/json; charset=utf-8")
	public String getTitleThyme(Model model) throws Exception {

		List<Book> allBooks = bookDao.findAll();
		System.err.println("----------------------findAllThyme-----------------------" );
		model.addAttribute("books", allBooks);
		// ModelAndView mav = new ModelAndView("booksPage");

		return "booksPage";
	}

	/**
	 * find book by ID
	 *
	 */
	@GetMapping("/{id}")
	public Map<String, Object> getBookById(@PathVariable String id) {
		return bookDao.getBookById(id);
	}

	/**
	 * Insert book(s)
	 *
	 */

	@PostMapping
	public Book insertBook(@RequestBody Book book) throws Exception {
		
		return bookDao.insertBook(book);
	}

	/**
	 * update book
	 *
	 */

	@PutMapping("/{id}")
	public Map<String, Object> updateBookById(@RequestBody Book book, @PathVariable String id) {
		return bookDao.updateBookById(id, book);
	}

	@DeleteMapping("/{id}")
	public void deleteBookById(@PathVariable String id) {
		bookDao.deleteBookById(id);
	}

}