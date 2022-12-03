package ru.rsreu.bookstore.api.books;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rsreu.bookstore.api.books.models.Book;
import ru.rsreu.bookstore.api.books.repositories.BookRepository;

import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(path = "/api/books", produces = "application/json")
public class BookController {
    private final BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<Book> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable("id") Long id) {
        Optional<Book> book = repository.findById(id);
        return book.map(value -> new ResponseEntity<>(value, OK))
                .orElseGet(() -> new ResponseEntity<>(null, NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Book create(@RequestBody Book book){
        return repository.save(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> put(@PathVariable("id") Long id, @RequestBody Book book){
        Optional<Book> sourceBook = repository.findById(id);
        if(!sourceBook.isPresent()) return new ResponseEntity<>(null, NOT_FOUND);
        book.setId(id);
        return new ResponseEntity<>(repository.save(book), OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Book> patch(@PathVariable("id") Long id, @RequestBody Book book){
        Optional<Book> optionalBook = repository.findById(id);
        if(!optionalBook.isPresent()) return new ResponseEntity<>(null, NOT_FOUND);
        Book sourceBook = optionalBook.get();
        if(book.getName() != null){
            sourceBook.setName(book.getName());
        }
        if(book.getAuthor() != null){
            sourceBook.setAuthor(book.getAuthor());
        }
        if(book.getPublishYear() != null){
            sourceBook.setPublishYear(book.getPublishYear());
        }
        if(book.getPublisher() != null){
            sourceBook.setPublisher(book.getPublisher());
        }
        if(book.getGenres() != null){
            sourceBook.setGenres(book.getGenres());
        }
        return new ResponseEntity<>(repository.save(sourceBook), OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable("id") Long id){
        Optional<Book> optionalBook = repository.findById(id);
        if(optionalBook.isPresent()){
            Book book = optionalBook.get();
            repository.delete(book);
        }
    }
}