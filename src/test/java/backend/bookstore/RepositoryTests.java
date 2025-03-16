package backend.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import backend.bookstore.domain.Book;
import backend.bookstore.domain.BookstoreRepository;
import backend.bookstore.domain.Category;
import backend.bookstore.domain.CategoryRepository;

@SpringBootTest
//@DataJPATest kun testataan sisäistä, esim. H2-kantaa

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//testit suoritetaan määritellyn tietokannan (esim. PostgreSQL) 
//kanssa sen sijaan, että käytettäisiin sisäistä testitietokantaa (esim. H2)

public class RepositoryTests {

    @Autowired
    private BookstoreRepository brepository; 
    @Autowired 
    CategoryRepository crepository;

    @Test
    public void findByAuthorShouldReturnBook () {
        List<Book> books = brepository.findByAuthor("Ville Virtanen");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("Kaunistamo");
    }

    @Test
    public void createNewBook () {
        Category category = new Category ("HORROR");
        crepository.save(category);
        Book book = new Book ("Uhrilampaat", "Stephen King", "88776554", 1986, 35.50, category);
        
        //Book book = new Book ("Uhrilampaat", "Stephen King", "88776554", 1986, 35.50, new Category("HORROR"));
        brepository.save(book);
        assertThat(book.getId()).isNotNull();
    }

    @Test
    public void deleteBook () {
        
		Category category = new Category("DRAMA");
        category = crepository.save(category);
        Book book = new Book("Aikaan sinikellojen", "Heikki Hietamies", "99887766", 1990, 25.00, category);
        book = brepository.save(book);
    
        assertThat(book.getId()).isNotNull();
    
        brepository.delete(book);
        List<Book> newBooks = brepository.findByAuthor("Heikki Hietamies");
        assertThat(newBooks).isEmpty();
     }
    }


