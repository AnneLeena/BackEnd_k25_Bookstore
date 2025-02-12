package backend.bookstore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BookstoreRepository extends CrudRepository<Book, Long> {

    List<Book> findByAuthor(String author);
    List<Book> findByCategoryName(String name);

 
}
