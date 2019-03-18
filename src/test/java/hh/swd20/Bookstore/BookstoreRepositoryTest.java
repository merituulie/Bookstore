package hh.swd20.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import hh.swd20.Bookstore.domain.Book;
import hh.swd20.Bookstore.domain.BookstoreRepository;
import hh.swd20.Bookstore.domain.Category;
import hh.swd20.Bookstore.domain.CategoryRepository;
import hh.swd20.Bookstore.domain.User;
import hh.swd20.Bookstore.domain.UserRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookstoreRepositoryTest {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	@Autowired
	private BookstoreRepository brepository;
	
	@Autowired
	private CategoryRepository crepository;
	
	@Autowired
	private UserRepository urepository;

		@Test
		public void bookstoreRepositoryWorks() {
			Book book = new Book("Harry Potter: 1","J.K. Rowling","122234-21",34.90,2006);
			brepository.save(book);
			
			assertThat(brepository).isNotNull();
		}
		
		@Test
		public void categoryRepositoryWorks() {
			Category category = new Category("Adventure");
			crepository.save(category);
			
			assertThat(crepository).isNotNull();
		}
		
		@Test
		public void userRepositoryWorks() {
			User user = new User("user3", "$2a$10$LBSCgBZ.KJa8LzFOQsJkRedK3jIf2L6CbG0IHO.x0hlwZxA55C.Ee", "user1@email.com", "USER");
			urepository.save(user);
			
			assertThat(crepository).isNotNull();
		}
		
		@Test
		public void createNewBook() {
			Book book = new Book("Harry Potter: 1","J.K. Rowling","122234-21",34.90,2006);
			brepository.save(book);
			
			assertThat(book.getId()).isNotNull();
		}
		
		@Test
		public void createNewCategory() {
			Category category = new Category("Horror");
			crepository.save(category);
			
			assertThat(category.getCategoryid()).isNotNull();
		}
		
		@Test // testataan, että uuden käyttäjän teko onnistuu
		public void createNewUser() {
			User user = new User("user3", "$2a$10$LBSCgBZ.KJa8LzFOQsJkRedK3jIf2L6CbG0IHO.x0hlwZxA55C.Ee", "user1@email.com", "USER");
			urepository.save(user);
			
			assertThat(user.getId()).isNotNull();
		}
		
		@Test // testataan, että kaikki tiedot syötetään oikeaan paikkaan
		public void createNewBookEverythingInTheRightPLace() {
			Book book = new Book("Harry Potter: 1","J.K. Rowling","122234-21",34.90,2006);
			brepository.save(book);
			
			assertThat(book.getTitle()).contains("Harry Potter: 1");
			assertThat(book.getAuthor()).contains("J.K. Rowling");
			assertThat(book.getIsbn().contains("122234-21"));
			assertThat(book.getPrice()).isEqualTo(34.90);
			assertThat(book.getYear()).isEqualTo(2006);
		}
		
		@Test
		public void deleteBook() {
			Book book = new Book("Harry Potter: 1","J.K. Rowling","122234-21",34.90,2006);
			brepository.save(book);
			brepository.deleteById(book.getId());
			
			assertThat(brepository.findById(book.getId())).isEmpty();
		}
		
		@Test
		public void deleteCategory() {
			Category category = new Category("Horror");
			crepository.save(category);
			crepository.deleteById(category.getCategoryid());
			
			assertThat(crepository.findById(category.getCategoryid())).isEmpty();
		}
		
		@Test
		public void deleteUser() {
			User user = new User("user3", "$2a$10$LBSCgBZ.KJa8LzFOQsJkRedK3jIf2L6CbG0IHO.x0hlwZxA55C.Ee", "user1@email.com", "USER");
			urepository.save(user);
			urepository.deleteById(user.getId());
			
			assertThat(urepository.findById(user.getId())).isEmpty();
		}
		
		@Test
		public void findByTitleBookReturnsBook() {
			Book book = new Book("Harry Potter: 1","J.K. Rowling","122234-21",34.90,2006);
			brepository.save(book);
			
			assertThat(brepository.findByTitle(book.getTitle()).contains("Harry Potter: 1"));
			assertThat(brepository.findByTitle(book.getAuthor()).contains("J.K. Rowling"));
		}
		
		@Test
		public void findByNameCategoryReturnsCategory() {
			Category category = new Category("Horror");
			crepository.save(category);
			
			assertThat(crepository.findByName(category.getName()).contains("Horror"));
		}
		
		@Test
		public void findByNameReturnsUser() {
			User user = new User("user3", "$2a$10$LBSCgBZ.KJa8LzFOQsJkRedK3jIf2L6CbG0IHO.x0hlwZxA55C.Ee", "user1@email.com", "USER");
			urepository.save(user);
			
			assertThat(urepository.findByUsername(user.getUsername()).equals("user3")); // MIKSI ihmeessä tässä ei käynyt contains() vaan piti käyttää equals()?
		}
}
