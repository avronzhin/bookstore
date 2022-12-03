package ru.rsreu.client;

public interface GenreService {

    Iterable<Genre> findAll();

    Genre addIngredient(Genre genre);

}