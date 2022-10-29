package ru.rsreu.bookstore.books.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "genres")
@NoArgsConstructor
@AllArgsConstructor
public class Genre {
    @Id
    private String id;
    private String title;
}