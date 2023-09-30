package ru.rsreu.bookstore.books.configurations;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.rsreu.bookstore.books.models.Genre;
import ru.rsreu.bookstore.books.repositories.GenreRepository;

@Configuration
public class DataLoaderConfiguration {
    @Bean
    public ApplicationRunner dataLoader(GenreRepository repo) {
        return args -> {
            repo.save(new Genre("DET", "детектив"));
            repo.save(new Genre("HN", "исторический роман"));
            repo.save(new Genre("LS", "любовный роман"));
            repo.save(new Genre("MY", "мистика"));
            repo.save(new Genre("ADV", "приключения"));
        };
    }
}
