package ru.rsreu.bookstore.books.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.rsreu.bookstore.books.models.Genre;

public interface GenreRepository extends CrudRepository<Genre, String> {}
