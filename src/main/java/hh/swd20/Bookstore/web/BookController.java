package hh.swd20.Bookstore.web;

import java.util.List;

import hh.swd20.Bookstore.domain.Book;
import hh.swd20.Bookstore.domain.BookstoreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController {
	@Autowired
	private BookstoreRepository repository;

	@RequestMapping(value="/booklist")
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	
	@RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book()); // annetaan yksi tyhjä olio
        return "addbook";
    }
	
	@PostMapping(value = "/save")
    public String saveBook(Book book){ // template syöttää name-attribuutin arvoista oliolle tiedot
		repository.save(book); // osaa tehdä niin insertin kuin updaten
        return "redirect:booklist";
	    } 
	
	@GetMapping(value = "/delete/{id}") // endpointin polkumuuttujaksi, jolla päästään kiinni id-muuttujaan
    public String deleteBook(@PathVariable("id") Long id, Model model) { // -> long tyyppinen polkumuuttuja
    	repository.deleteById(id);
        return "redirect:../booklist"; // .. pääsee taaksepäin
    }
	
	@RequestMapping(value = "/edit/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("book", repository.findById(id)); 
        return "editbook";
    }
	
}
