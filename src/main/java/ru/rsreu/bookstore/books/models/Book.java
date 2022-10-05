package ru.rsreu.bookstore.books.models;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
public class Book {

    private Long id;

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    @NotNull
    @Size(min = 5, message = "Author must be at least 5 characters long")
    private String author;

    private List<Genre> genres = new ArrayList<>();

    @Range(min = 1700, max = 2100, message = "Publish year must be between 1700 and 2100")
    private int publishYear = 2022;
}