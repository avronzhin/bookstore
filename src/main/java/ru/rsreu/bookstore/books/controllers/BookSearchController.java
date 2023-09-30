package ru.rsreu.bookstore.books.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.rsreu.bookstore.books.models.Book;
import ru.rsreu.bookstore.books.models.BookSearch;
import ru.rsreu.bookstore.books.repositories.BookRepository;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/search")
public class BookSearchController {
    private final BookRepository repository;

    @Autowired
    public BookSearchController(BookRepository repository) {
        this.repository = repository;
    }

    @ModelAttribute(name = "bookSearch")
    public BookSearch bookSearch() {
        return new BookSearch();
    }

    @GetMapping
    public String get(){
        return "search";
    }

    @PostMapping
    public String post(BookSearch bookSearch, Model model){
        Iterable<Book> allBooks = repository.findAll();
        Predicate<Book> searchPredicate = createSearchPredicate(bookSearch);
        List<Book> books = StreamSupport.stream(allBooks.spliterator(), false)
                .filter(searchPredicate)
                .collect(Collectors.toList());
        model.addAttribute("books", books);
        return "books";
    }

    private static Predicate<Book> createSearchPredicate(BookSearch bookSearch){
        return book -> {
            if(!bookSearch.getBookNameSearch().isEmpty()){
                if(!book.getName().contains(bookSearch.getBookNameSearch())) return false;
            }
            if(!bookSearch.getAuthorSearch().isEmpty()){
                return book.getAuthor().contains(bookSearch.getAuthorSearch());
            }
            return true;
        };
    }
}
