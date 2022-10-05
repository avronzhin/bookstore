package ru.rsreu.bookstore.books.repositories;

import ru.rsreu.bookstore.books.models.Book;

public interface BookRepository{
    Book save(Book book);
}
