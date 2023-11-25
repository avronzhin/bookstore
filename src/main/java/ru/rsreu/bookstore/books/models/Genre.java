package ru.rsreu.bookstore.books.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Genre {

    @Id
    private String id;

    @NotNull
    private String title;

    @ManyToMany
    private List<Book> booksOfThisGenre = new ArrayList<>();

    public Genre(String id, String title) {
        this.id = id;
        this.title = title;
    }
}