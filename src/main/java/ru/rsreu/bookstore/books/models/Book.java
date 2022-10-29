package ru.rsreu.bookstore.books.models;

import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.rsreu.bookstore.security.models.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Document(collection = "books")
@NoArgsConstructor
@AllArgsConstructor
public class Book{

    @Id
    private String id;

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    @NotNull
    @Size(min = 5, message = "Author must be at least 5 characters long")
    private String author;

    private List<Genre> genres = new ArrayList<>();

    @Range(min = 1700, max = 2100, message = "Publish year must be between 1700 and 2100")
    private int publishYear = 2022;

    private User publisher;

    public String getGenresText(){
        return "Genres: " +
                genres.stream().map(Genre::getTitle).collect(Collectors.joining(", ")) +
                ".";
    }
}