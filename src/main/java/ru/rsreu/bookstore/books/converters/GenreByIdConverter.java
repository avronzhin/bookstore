package ru.rsreu.bookstore.books.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.rsreu.bookstore.books.models.Genre;
import ru.rsreu.bookstore.books.repositories.GenreRepository;

@Component
public class GenreByIdConverter implements Converter<String, Genre> {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreByIdConverter(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre convert(String id) {
        return genreRepository.findById(id).orElse(null);
    }
}
