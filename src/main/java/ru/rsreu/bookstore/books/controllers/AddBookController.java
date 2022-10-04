package ru.rsreu.bookstore.books.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.rsreu.bookstore.books.models.Book;
import ru.rsreu.bookstore.books.models.Genre;
import ru.rsreu.bookstore.books.repositories.BookRepository;
import ru.rsreu.bookstore.books.repositories.GenreRepository;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/book/add")
@SessionAttributes("book")
public class AddBookController {

    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    @Autowired
    public AddBookController(GenreRepository genreRepository, BookRepository bookRepository) {
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
        return "add_book";
    }

    @PostMapping
    public String addBook(@Valid Book book, Errors errors) {
        if (errors.hasErrors()) {
            return "add_book";
        }
        bookRepository.save(book);
        return "redirect:/book/add/success";
    }
}