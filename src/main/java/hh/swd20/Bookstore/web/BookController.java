package hh.swd20.Bookstore.web;

import java.util.List;
import java.util.Optional;

import hh.swd20.Bookstore.domain.Book;
import hh.swd20.Bookstore.domain.BookstoreRepository;
import hh.swd20.Bookstore.domain.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookController {
	@Autowired
	private BookstoreRepository repository;
	
	@Autowired
	private CategoryRepository crepository;
	
	@RequestMapping(value="/login") // itse pääsin edellinen-painikkeella uloskirjautumisen jälkeenkin vielä sivuille. Miten sen voisi estää? Sessiolaskurilla?
	public String login() {
		return "login";
	}

	@RequestMapping(value="/booklist")
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	
	//RESTfull homma
	@GetMapping(value="/books")
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) repository.findAll();
	}
	
	//RESTfull homma
	@GetMapping(value="/books/{id}")
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
		return repository.findById(bookId);
	}
	
	
	@RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book()); // annetaan yksi tyhjä olio
        model.addAttribute("categories", crepository.findAll());
    	return "addbook";
    }
	
	@PostMapping(value = "/save")
    public String saveBook(Book book){ // template syöttää name-attribuutin arvoista oliolle tiedot
		repository.save(book); // osaa tehdä niin insertin kuin updaten
        return "redirect:booklist";
	    }
	
	@GetMapping(value = "/delete/{id}") // endpointin polkumuuttujaksi, jolla päästään kiinni id-muuttujaan
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteBook(@PathVariable("id") Long id, Model model) { // -> long tyyppinen polkumuuttuja
    	repository.deleteById(id);
        return "redirect:../booklist"; // .. pääsee taaksepäin
    }
	
	@RequestMapping(value = "/edit/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("book", repository.findById(id));
    	model.addAttribute("categories", crepository.findAll());
        return "editbook";
    }
	
}
