package ru.rsreu.bookstore.books.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookSearch {
    private String title = "";
    private String author = "";
    private String genre = "";
}
