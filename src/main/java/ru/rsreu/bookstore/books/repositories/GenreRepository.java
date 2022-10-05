package ru.rsreu.bookstore.books.repositories;

import ru.rsreu.bookstore.books.models.Genre;

import java.util.Optional;

public interface GenreRepository{
    Iterable<Genre> getAll();

    Optional<Genre> getById(String id);

    Genre save(Genre genre);
}
