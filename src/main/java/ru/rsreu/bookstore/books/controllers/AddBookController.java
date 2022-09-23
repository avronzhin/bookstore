package ru.rsreu.bookstore.books.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.rsreu.bookstore.books.models.Book;
import ru.rsreu.bookstore.books.models.Genre;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collection;

@Slf4j
@Controller
@RequestMapping("/book/add")
@SessionAttributes("book")
public class AddBookController {

    @ModelAttribute
    public void addGenresToModel(Model model) {
        Collection<Genre> genres = Arrays.asList(
                new Genre("DET", "detective"),
                new Genre("HNO", "historical novel"),
                new Genre("LS", "love story"),
                new Genre("MYS", "mystic"),
                new Genre("ADV", "adventures")
        );
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
        log.info("Adding new book: {}", book);
        return "redirect:/book/add/success";
    }
}