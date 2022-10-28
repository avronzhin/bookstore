package ru.rsreu.bookstore.books.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.rsreu.bookstore.books.models.Book;
import ru.rsreu.bookstore.books.models.Genre;
import ru.rsreu.bookstore.books.repositories.BookRepository;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @ModelAttribute
    public void addGenresToModel(Model model) {
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
    }

    @GetMapping("/all")
    public String showBooksForm(){
        return "books";
    }
}
