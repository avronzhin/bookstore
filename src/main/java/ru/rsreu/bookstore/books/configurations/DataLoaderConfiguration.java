package ru.rsreu.bookstore.books.configurations;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import ru.rsreu.bookstore.books.models.Book;
import ru.rsreu.bookstore.books.models.Genre;
import ru.rsreu.bookstore.books.repositories.BookRepository;
import ru.rsreu.bookstore.books.repositories.GenreRepository;
import ru.rsreu.bookstore.security.models.User;
import ru.rsreu.bookstore.security.repositories.UserRepository;


import java.util.ArrayList;

@Configuration
public class DataLoaderConfiguration {
    @Bean
    public ApplicationRunner genresLoader(GenreRepository repo) {
        return args -> {
            repo.save(new Genre("DET", "detective"));
            repo.save(new Genre("HN", "historical novel"));
            repo.save(new Genre("LS", "love story"));
            repo.save(new Genre("MY", "mystic"));
            repo.save(new Genre("ADV", "adventures"));
        };
    }

    @Bean
    @Profile("!prod")
    public CommandLineRunner booksLoader(BookRepository repo, UserRepository userRepository) {
        return args -> {
            User user = new User("testUser", "");
            userRepository.save(user);

            for(int i = 0; i < 100; i++){
                Book book = new Book();
                book.setName("TestBook" + i);
                book.setAuthor("TestAuthor");
                book.setPublishYear(2002);
                book.setGenres(new ArrayList<>());
                book.setPublisher(user);
                repo.save(book);
            }
        };
    }
}
