package ru.rsreu.bookstore.security.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.rsreu.bookstore.security.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User getByUsername(String username);
}
