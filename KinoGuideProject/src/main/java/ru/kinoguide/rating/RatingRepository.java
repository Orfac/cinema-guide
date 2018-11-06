package ru.kinoguide.rating;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)

public interface RatingRepository extends CrudRepository<Rating, Integer> {
    List<Rating> findAll();
}
