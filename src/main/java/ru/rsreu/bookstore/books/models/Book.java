package ru.rsreu.bookstore.books.models;

import lombok.*;
import org.hibernate.validator.constraints.Range;
import ru.rsreu.bookstore.security.models.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Book{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotEmpty
    @Size(min = 1, message = "Name must be at least 5 characters long")
    private String title;

    @NotNull
    @Size(min = 5, message = "Author must be at least 5 characters long")
    private String author;

    @ManyToMany
    private List<Genre> genres = new ArrayList<>();

    @Range(min = 1700, max = 2100, message = "Publish year must be between 1700 and 2100")
    private int publishYear = 2022;

    @ManyToOne
    private User publisher;

    public String getGenresText(){
        return "Жанры: " +
                genres.stream().map(Genre::getTitle).collect(Collectors.joining(", ")) +
                ".";
    }
}