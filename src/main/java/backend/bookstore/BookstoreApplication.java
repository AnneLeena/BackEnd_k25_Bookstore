package backend.bookstore;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import backend.bookstore.domain.Book;
import backend.bookstore.domain.BookstoreRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookstoreDemo (BookstoreRepository repository) {
		return (args) -> {
			log.info("save a couple of books");
			repository.save(new Book("Omenapuu", "Laila Hietamies", "987-dölj-878",1990, 19.90));
			repository.save(new Book("Missä muruseni","Kari Hotakainen", "098-ökj-98",  2021, 26.70));	
			repository.save(new Book("Kaunistamo","Ville Virtanen", "898548766",  2005, 35.50));
		
		};
	}

}
