package ru.rsreu.bookstore.books.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.rsreu.bookstore.books.models.Book;
import ru.rsreu.bookstore.books.models.BookSearch;
import ru.rsreu.bookstore.books.models.ErrorMessage;
import ru.rsreu.bookstore.books.models.Genre;
import ru.rsreu.bookstore.books.repositories.BookRepository;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    private static Predicate<Book> createSearchPredicate(BookSearch bookSearch) {
        return book -> {
            if (!bookSearch.getTitle().isEmpty()) {
                if (!book.getTitle().contains(bookSearch.getTitle())) return false;
            }
            if (!bookSearch.getAuthor().isEmpty()) {
                return book.getAuthor().contains(bookSearch.getAuthor());
            }
            if (!bookSearch.getGenre().isEmpty()) {
                return book.getGenres().stream()
                        .map(Genre::getTitle)
                        .collect(Collectors.toList())
                        .contains(bookSearch.getGenre());
            }
            
            return true;
        };
    }

    @GetMapping("/show")
    public String showBooksForm(Model model, BookSearch bookSearch, ErrorMessage errorMessage) {
        model.addAttribute("errorMessage", errorMessage.getMessage());
        model.addAttribute("bookSearch", bookSearch);
        Iterable<Book> allBooks = bookRepository.findAll();
        Predicate<Book> searchPredicate = createSearchPredicate(bookSearch);
        List<Book> books = StreamSupport.stream(allBooks.spliterator(), false).filter(searchPredicate).collect(Collectors.toList());
        model.addAttribute("books", books);
        return "books";
    }
}
