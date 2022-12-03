package ru.rsreu.bookstore.api.books.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import ru.rsreu.bookstore.security.models.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    @NotNull
    @Size(min = 5, message = "Author must be at least 5 characters long")
    private String author;

    @ManyToMany
    private List<Genre> genres = new ArrayList<>();

    @Range(min = 1700, max = 2100, message = "Publish year must be between 1700 and 2100")
    private Integer publishYear = 2022;

    @ManyToOne
    private User publisher;
}