package ru.kinoguide.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional(readOnly = true)
public interface UserRepository extends CrudRepository<User, Integer> {
//    @Cacheable("users") ? TODO

    User findByName(String name);

}
