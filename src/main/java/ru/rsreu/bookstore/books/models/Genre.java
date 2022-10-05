package ru.rsreu.bookstore.books.models;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Genre {
    private String id;
    private String title;
}