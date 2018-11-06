package ru.kinoguide.films;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface FilmRepository extends CrudRepository<Film, Integer> {
    List<Film> findAll();
}
