package ru.rsreu.bookstore.books.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookSearch {
    private String bookNameSearch = "";
    private String authorSearch = "";
    private String genre = "";
}
