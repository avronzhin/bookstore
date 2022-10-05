package ru.rsreu.bookstore.books.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.rsreu.bookstore.books.models.Book;
import ru.rsreu.bookstore.books.models.Genre;

import java.sql.Types;
import java.util.Arrays;
import java.util.List;

@Repository
public class JDBCBookRepository implements BookRepository {
    private final JdbcOperations jdbcOperations;

    @Autowired
    public JDBCBookRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    @Transactional
    public Book save(Book book) {
        PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory("insert into Book (name, author, publish_year) VALUES ( ?, ?, ?)", Types.VARCHAR, Types.VARCHAR, Types.INTEGER);
        factory.setReturnGeneratedKeys(true);
        PreparedStatementCreator creator = factory.newPreparedStatementCreator(Arrays.asList(book.getName(), book.getAuthor(), book.getPublishYear()));
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(creator, keyHolder);
        long id = keyHolder.getKey().longValue();
        book.setId(id);
        saveGenreRefs(id, book.getGenres());
        return book;
    }

    private void saveGenreRefs(long id, List<Genre> genres) {
        for (Genre genre : genres) {
            jdbcOperations.update("insert into Genre_Ref(book_id, genre_id) values ( ?, ? )", id, genre.getId());
        }
    }
}
