package ru.rsreu.bookstore.api.books.configurations;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import ru.rsreu.bookstore.api.books.models.Book;
import ru.rsreu.bookstore.api.books.models.Genre;
import ru.rsreu.bookstore.api.books.repositories.BookRepository;
import ru.rsreu.bookstore.api.books.repositories.GenreRepository;
import ru.rsreu.bookstore.security.models.User;
import ru.rsreu.bookstore.security.repositories.UserRepository;


import java.util.ArrayList;
import java.util.Collection;

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
        User user = new User("testUser", "");
        userRepository.save(user);

        Collection<Book> books = new ArrayList<>();
        books.add(createBook("War and Peace", "L. N. Tolstoy", 2000,  user));
        books.add(createBook("Evgeniy Onegin", "A. S. Pushkin", 2002,  user));
        books.add(createBook("Dead Souls", "Gogol", 2004,  user));
        books.add(createBook("Crime and Punishment", "Dostoevsky", 2000,  user));
        books.add(createBook("Idiot", "Dostoevsky", 1998,  user));

        return args -> repo.saveAll(books);
    }

    private static Book createBook(String name, String author, int year, User user){
        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setPublishYear(year);
        book.setGenres(new ArrayList<>());
        book.setPublisher(user);
        return book;
    }
}
