package hh.swd20.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import hh.swd20.Bookstore.web.BookController;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookstoreApplicationTests {
	
	@Autowired
	private BookController bcontroller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(bcontroller).isNotNull();
	}

}

