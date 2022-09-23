package ru.rsreu.bookstore.books.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.rsreu.bookstore.books.models.Genre;

import java.util.HashMap;
import java.util.Map;

@Component
public class GenreByIdConverter implements Converter<String, Genre> {
    private final Map<String, Genre> genreMap = new HashMap<>();

    public GenreByIdConverter() {
        genreMap.put("DET", new Genre("DET", "detective"));
        genreMap.put("HNO", new Genre("HNO", "historical novel"));
        genreMap.put("LS", new Genre("LS", "love story"));
        genreMap.put("MYS", new Genre("MYS", "mystic"));
        genreMap.put("ADV", new Genre("ADV", "adventures"));
    }

    @Override
    public Genre convert(String id) {
        return genreMap.get(id);
    }
}
