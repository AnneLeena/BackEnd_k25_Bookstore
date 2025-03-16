package backend.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import backend.bookstore.domain.BookstoreRepository;
import backend.bookstore.domain.CategoryRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMethod;
import backend.bookstore.domain.Book;




@Controller

public class BookController {

    private final BookstoreRepository repository;

    public BookController(BookstoreRepository brepository) {
        this.repository = brepository;
    }

        @Autowired
	    private BookstoreRepository brepository; 

	    @Autowired
	    private CategoryRepository crepository; 

    // jos tehty oma login.html-page - tarvitaan seuraava: ks.secureStudentListUser -demo 26.2.
    //@RequestMapping{value="/login"}
    //public String login() {
      //  return "login";
    //}
    

    @RequestMapping(value= {"/", "/booklist"})
    public String bookList (Model model) {
        model.addAttribute("books", brepository.findAll());

        return "booklist";
    }
    
    @RequestMapping(value= "/add")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", crepository.findAll());
        return "addbook";
    }
    @PreAuthorize("hasAuthority('ADMIN')") //user näkee lisäyksen, mutta ei voi lisätä, koska metodi kielletty
    @RequestMapping(value = "/save", method=RequestMethod.POST)
    public String save (@Valid @ModelAttribute ("book") Book book,BindingResult bindingResult,  Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("Errors errors " + book);
            model.addAttribute("book", book);
            
            return "addbook";
        }
        repository.save(book);
        return "redirect:/booklist";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method=RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId) {
        repository.deleteById(bookId);
        return "redirect:/booklist";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/edit/{id}", method=RequestMethod.GET)
    public String editBook(@PathVariable("id") Long bookId, Model model) {
        Book book = repository.findById(bookId).orElse(null);
        model.addAttribute("book", book);
        model.addAttribute("categories", crepository.findAll());
        return "editbook";
    }
    
    
}