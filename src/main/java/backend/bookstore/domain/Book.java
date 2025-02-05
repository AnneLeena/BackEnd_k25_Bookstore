package backend.bookstore.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty
    private String title;

    @NotEmpty
    private String author;

    @Size (min = 6, max = 20)
    private String isbn;

    @Min (1900)
    @Max (2025)
    private int publicationYear;

    private double price; 


    @ManyToOne
    @JoinColumn(name="categoryId")
    private Category category;


    public Book(){
    }

    public Book(String title, String author, String isbn, int publicationYear, double price) {
        super();
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.price = price;
    }

    
    public Book(@NotEmpty String title, @NotEmpty String author, @Size(min = 6, max = 20) String isbn,
            int publicationYear, double price, Category category) {
        super();
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.price = price;
        this.category = category;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    } 
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public int getPublicationYear() {
        return publicationYear;
    }
    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", author=" + author + ", isbn=" + isbn + ", publicationYear="
                + publicationYear + ", price=" + price + ", category=" + this.getCategory() + "]";
    }
        

}