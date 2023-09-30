package ru.rsreu.bookstore.books.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.rsreu.bookstore.books.repositories.BookRepository;

@Controller
@RequestMapping("/book/delete")
public class BookDeletingController {
    private final BookRepository bookRepository;

    @Autowired
    public BookDeletingController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostMapping
    public String delete(@RequestParam long bookId){
        bookRepository.deleteById(bookId);
        return "redirect:/book/all";
    }
}
