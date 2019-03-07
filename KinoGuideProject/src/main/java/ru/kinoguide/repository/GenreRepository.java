package ru.kinoguide.repository;

import org.springframework.data.repository.CrudRepository;
import ru.kinoguide.entity.Genre;

public interface GenreRepository extends CrudRepository<Genre, Integer> {
}
