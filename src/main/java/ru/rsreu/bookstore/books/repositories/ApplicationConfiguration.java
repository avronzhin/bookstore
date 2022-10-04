package ru.rsreu.bookstore.books.repositories;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import ru.rsreu.bookstore.books.models.Genre;

@org.springframework.context.annotation.Configuration
public class ApplicationConfiguration {
    @Bean
    public ApplicationRunner dataLoader(GenreRepository repo) {
        return args -> {
            repo.save(new Genre("DET", "detective"));
            repo.save(new Genre("HN", "historical novel"));
            repo.save(new Genre("LS", "love story"));
            repo.save(new Genre("MY", "mystic"));
            repo.save(new Genre("ADV", "adventures"));
        };
    }
}
