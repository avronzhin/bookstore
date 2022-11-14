package ru.rsreu.bookstore.books.controllers;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="books")
@Data
public class BookControllerProps {
    private int pageSize = 10;
}
