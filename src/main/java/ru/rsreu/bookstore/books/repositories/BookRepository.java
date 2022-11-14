package ru.rsreu.bookstore.books.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.rsreu.bookstore.books.models.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
    Page<Book> findAll(Pageable pageable);
}
