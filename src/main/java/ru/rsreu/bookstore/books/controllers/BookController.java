package ru.rsreu.bookstore.books.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.rsreu.bookstore.books.models.Book;
import ru.rsreu.bookstore.books.repositories.BookRepository;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookRepository bookRepository;
    private final BookControllerProps props;

    public BookController(BookRepository bookRepository, BookControllerProps props) {
        this.bookRepository = bookRepository;
        this.props = props;
    }

    @ModelAttribute
    public void addGenresToModel(Model model) {
        Pageable pageable = PageRequest.of(0, props.getPageSize());
        Page<Book> books = bookRepository.findAll(pageable);
        model.addAttribute("books", books);
    }

    @GetMapping("/all")
    public String showBooksForm(){
        return "books";
    }
}
