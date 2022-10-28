package ru.rsreu.bookstore.books.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;


@Data
@Table("genres")
@NoArgsConstructor
@AllArgsConstructor
public class Genre {
    @PrimaryKey
    private String id;
    private String title;
}