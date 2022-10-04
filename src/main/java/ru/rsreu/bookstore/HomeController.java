package ru.rsreu.bookstore;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.rsreu.bookstore.books.models.Genre;
import ru.rsreu.bookstore.books.repositories.GenreRepository;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home";
    }
}