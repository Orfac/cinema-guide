package ru.kinoguide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.kinoguide.entity.User;

import java.util.Collection;

@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);
}
