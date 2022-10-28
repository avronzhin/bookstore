package ru.rsreu.bookstore.books.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.rsreu.bookstore.books.models.Book;

import java.util.UUID;

public interface BookRepository extends CrudRepository<Book, UUID>{

}
