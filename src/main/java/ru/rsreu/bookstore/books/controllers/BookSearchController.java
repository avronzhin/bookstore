package ru.rsreu.bookstore.books.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.rsreu.bookstore.books.models.BookSearch;

@Controller
@RequestMapping("/search")
public class BookSearchController {
    @ModelAttribute(name = "bookSearch")
    public BookSearch bookSearch() {
        return new BookSearch();
    }

    @GetMapping
    public String get() {
        return "search";
    }
}
