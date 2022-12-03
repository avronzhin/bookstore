package ru.rsreu.bookstore.api.books;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.rsreu.bookstore.api.books.models.Genre;
import ru.rsreu.bookstore.api.books.repositories.GenreRepository;

@RestController
@RequestMapping(path = "/api/genres", produces = "application/json")
public class GenreController {
    private final GenreRepository repository;

    public GenreController(GenreRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<Genre> getAll() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Genre saveIngredient(@RequestBody Genre genre) {
        return repository.save(genre);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIngredient(@PathVariable("id") String genreId) {
        repository.deleteById(genreId);
    }
}