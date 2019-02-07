package hh.swd20.Bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

	@GetMapping("/index")
	public String teeKirja() {
		return null;
	}
}
