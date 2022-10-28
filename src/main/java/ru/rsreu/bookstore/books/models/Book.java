package ru.rsreu.bookstore.books.models;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import ru.rsreu.bookstore.security.models.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Table("books")
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @PrimaryKey
    private UUID id = Uuids.timeBased();

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    @NotNull
    @Size(min = 5, message = "Author must be at least 5 characters long")
    private String author;

    @Column("genres")
    private List<GenreUDT> genres = new ArrayList<>();

    @Range(min = 1700, max = 2100, message = "Publish year must be between 1700 and 2100")
    private int publishYear = 2022;

    private UserUDT publisher;

    public void setPublisher(User publisher) {
        this.publisher = new UserUDT(publisher.getUsername(), publisher.getPassword());
    }

    public void addGenre(Genre genre){
        genres.add(new GenreUDT(genre.getTitle()));
    }

    public String getGenresText() {
        return "Genres: " +
                genres.stream().map(GenreUDT::getTitle).collect(Collectors.joining(", ")) +
                ".";
    }
}