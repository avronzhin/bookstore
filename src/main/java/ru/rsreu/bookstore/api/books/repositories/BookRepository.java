package ru.rsreu.bookstore.api.books.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.rsreu.bookstore.api.books.models.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
}
