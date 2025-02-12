package backend.bookstore.web;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import backend.bookstore.domain.Book;
import backend.bookstore.domain.BookstoreRepository;
import backend.bookstore.domain.CategoryRepository;

@RestController

public class BookRESTController {
    
    private static final Logger log = LoggerFactory.getLogger(BookRESTController.class);

    private final BookstoreRepository bookstoreRepository;
    private CategoryRepository categoryRepository;

    public BookRESTController(BookstoreRepository bookstoreRepository, CategoryRepository categoryRepository) {
        this.bookstoreRepository = bookstoreRepository;
        this.categoryRepository = categoryRepository;

    }

    @GetMapping("/books")
    public Iterable<Book> getBooks() {
        log.info("//fetch and return books");
        return bookstoreRepository.findAll();
    }

    @PostMapping("books")
    Book newBook(@RequestBody Book newBook) {
        log.info("save new book " + newBook);
        return bookstoreRepository.save(newBook);
    }

    
    @PutMapping("/books/{id}")
    Book editBook(@RequestBody Book editedBook, @PathVariable Long id) {
        log.info("edit book " + editedBook);
        editedBook.setId(id);
        return bookstoreRepository.save(editedBook);
    }

    // // delete car
    // @DeleteMapping("/cars/{id}")
    // void deleteCar(@PathVariable Long id) {
    // carRepository.deleteById(id);
    // }

    
    @DeleteMapping("/books/{id}")
    public Iterable<Book> deleteBook(@PathVariable Long id) {
        log.info("delete car, id = " + id);
        bookstoreRepository.deleteById(id);
        return bookstoreRepository.findAll();
    }

    
    @GetMapping("/books/{id}")
    Optional<Book> getBook(@PathVariable Long id) {
        log.info("find book, id = " + id);
        return bookstoreRepository.findById(id);
    }

    // find one car with the specific owner
    //@GetMapping("/books/category/{lastname}")
    //List<Car> getCarByOwner(@PathVariable String lastname) {
       // log.info("find car, lastname = " + lastname);
       // return carRepository.findByOwnerLastName(lastname);
    //}

}
