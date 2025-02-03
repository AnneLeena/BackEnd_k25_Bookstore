package backend.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import backend.bookstore.domain.BookstoreRepository;

@Controller

public class BookController {

    private final BookstoreRepository repository;

    public BookController(BookstoreRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value= {"/", "/booklist"})
    public String bookList (Model model) {
        model.addAttribute("books", repository.findAll());

        return "booklist";
    }    
    
}