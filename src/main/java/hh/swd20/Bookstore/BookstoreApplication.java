package hh.swd20.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import hh.swd20.Bookstore.domain.Book;
import hh.swd20.Bookstore.domain.BookstoreRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookstoreRepository repository) {
		return (args) -> {
			log.info("save a couple of students");
			repository.save(new Book("And then there were none","Agatha Christie","123123-21",14.90,1939)); // tänne kirjojen tiedot!
			repository.save(new Book("Veljeni leijonamieli","Astrid Lingren","223123-12",13.80,1973));	
			
			log.info("fetch all students");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
		};
		
	}
}

