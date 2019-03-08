package ru.kinoguide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.kinoguide.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
