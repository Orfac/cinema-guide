package ru.kinoguide.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.kinoguide.Entity.User;

import java.util.Collection;

@Transactional(readOnly = true)
public interface UserRepository extends CrudRepository<User, Integer> {
//    @Cacheable("users") ? TODO

    User findByName(String name);
    Collection<User> findAll();
}
