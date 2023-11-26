package ru.rsreu.bookstore.books.configurations;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.rsreu.bookstore.books.models.Genre;
import ru.rsreu.bookstore.books.repositories.GenreRepository;

@Configuration
public class DataLoaderConfiguration {
    @Bean
    public ApplicationRunner dataLoader(GenreRepository genreRepository) {
        return args -> {
            loadGenres(genreRepository);
        };
    }

    private void loadGenres(GenreRepository genreRepository) {
        genreRepository.save(new Genre("DET", "детектив"));
        genreRepository.save(new Genre("HN", "исторический роман"));
        genreRepository.save(new Genre("LS", "любовный роман"));
        genreRepository.save(new Genre("MY", "мистика"));
        genreRepository.save(new Genre("ADV", "приключения"));
    }
}
