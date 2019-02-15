package hh.swd20.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.Bookstore.domain.Book;
import hh.swd20.Bookstore.domain.BookstoreRepository;
import hh.swd20.Bookstore.domain.Category;
import hh.swd20.Bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookstoreRepository brepository, CategoryRepository crepository) {
		return (args) -> {
			
			Category category1 = new Category("Thriller");
			crepository.save(category1);
			brepository.save(new Book("And then there were none","Agatha Christie","123123-21",14.90,1939,category1)); // tänne kirjojen tiedot!
			
			Category category2 = new Category("Adventure");
			crepository.save(category2);
			brepository.save(new Book("Veljeni leijonamieli","Astrid Lingren","223123-12",13.80,1973,category2));	
			
			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}
			
		};
		
	}
}