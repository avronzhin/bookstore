package ru.rsreu.bookstore.books.models;

import lombok.*;
import org.hibernate.validator.constraints.Range;
import ru.rsreu.bookstore.security.models.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 5, message = "Название книги должно содержать хотя бы 5 символов")
    private String title;

    @NotNull
    @Size(min = 5, message = "Имя автора должно содержать хотя бы 5 символов")
    private String author;

    @ManyToMany
    private List<Genre> genres = new ArrayList<>();

    @Range(min = 1700, max = 2100, message = "Год публикации должен быть в диапазоне [1700, 2100]")
    private int publishYear = 2022;

    @ManyToOne
    private User publisher;

    public String getGenresText() {
        return "Жанры: " +
                genres.stream().map(Genre::getTitle).collect(Collectors.joining(", ")) +
                ".";
    }
}