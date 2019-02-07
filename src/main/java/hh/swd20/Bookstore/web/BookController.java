package hh.swd20.Bookstore.web;

import java.util.List;

import hh.swd20.Bookstore.domain.Book;
import hh.swd20.Bookstore.domain.BookstoreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {
	@Autowired
	private BookstoreRepository repository;

	@GetMapping(value="/booklist")
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
}
