package backend.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import backend.bookstore.domain.BookstoreRepository;
import org.springframework.web.bind.annotation.RequestMethod;
import backend.bookstore.domain.Book;
import org.springframework.web.bind.annotation.RequestParam;



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
    
    @RequestMapping(value= "/add")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }

    @RequestMapping(value = "/save", method=RequestMethod.POST)
    public String save (Book book) {
        repository.save(book);
        return "redirect:/booklist";
    }

    @RequestMapping(value = "/delete/{id}", method=RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId) {
        repository.deleteById(bookId);
        return "redirect:/booklist";
    }

    @RequestMapping(value = "/edit/{id}", method=RequestMethod.GET)
    public String editBook(@PathVariable("id") Long bookId, Model model) {
        Book book = repository.findById(bookId).orElse(null);
        model.addAttribute("book", book);
        return "editbook";
    }

    @RequestMapping(value= "/update", method=RequestMethod.POST)
    public String update (Book book) {
        repository.save(book);
        return "redirect:/booklist";
        
    }
    
      
    
    
}