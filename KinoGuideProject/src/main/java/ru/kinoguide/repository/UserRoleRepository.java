package ru.kinoguide.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kinoguide.entity.UserRole;

import java.util.List;

public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {
     List<UserRole> findByName(String name);
}
