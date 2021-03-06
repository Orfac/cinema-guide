package ru.kinoguide.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.kinoguide.entity.User;

import java.util.Collection;

@Transactional(readOnly = true)
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByName(String name);
    Collection<User> findAll();
}
