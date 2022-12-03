package ru.rsreu.bookstore.api.books.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.rsreu.bookstore.api.books.models.Genre;

public interface GenreRepository extends CrudRepository<Genre, String> {}
