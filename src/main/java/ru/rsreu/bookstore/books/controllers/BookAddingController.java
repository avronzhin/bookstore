package ru.rsreu.bookstore.books.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.rsreu.bookstore.books.models.Book;
import ru.rsreu.bookstore.books.models.Genre;
import ru.rsreu.bookstore.books.repositories.BookRepository;
import ru.rsreu.bookstore.books.repositories.GenreRepository;
import ru.rsreu.bookstore.security.models.User;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/book/add")
@SessionAttributes("book")
public class BookAddingController {
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    @Autowired
    public BookAddingController(GenreRepository genreRepository, BookRepository bookRepository) {
        this.genreRepository = genreRepository;
        this.bookRepository = bookRepository;
    }

    @ModelAttribute
    public void addGenresToModel(Model model) {
        Iterable<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);
    }

    @ModelAttribute(name = "book")
    public Book book() {
        return new Book();
    }

    @GetMapping
    public String showAddBookForm() {
        return "book_adding";
    }

    @PostMapping
    public String addBook(
            @Valid Book book,
            Errors errors,
            SessionStatus sessionStatus,
            @AuthenticationPrincipal User user
    ) {
        if (errors.hasErrors()) {
            return "book_adding";
        }

        book.setPublisher(user);

        bookRepository.save(book);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}