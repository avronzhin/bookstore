package ru.rsreu.bookstore.books.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.rsreu.bookstore.books.models.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JDBCGenreRepository implements GenreRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JDBCGenreRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<Genre> getAll() {
        return jdbcTemplate.query(
                "select * from Genre",
                this::mapRowToGenre
        );
    }

    private Genre mapRowToGenre(ResultSet resultSet, int i) throws SQLException {
        return new Genre(resultSet.getString("id"), resultSet.getString("title"));
    }

    @Override
    public Optional<Genre> getById(String id) {
        List<Genre> results = jdbcTemplate.query(
                "select * from Genre where id = ?",
                this::mapRowToGenre,
                id
        );
        return results.size() == 0 ? Optional.empty() : Optional.of(results.get(0));
    }

    @Override
    public Genre save(Genre genre) {
        jdbcTemplate.update(
                "insert into Genre(id, title) values ( ?, ? )", genre.getId(), genre.getTitle()
        );
        return genre;
    }
}
