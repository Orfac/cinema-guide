package ru.kinoguide.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.kinoguide.entity.CinemaTheatre;

public interface CinemaTheatreRepository extends PagingAndSortingRepository<CinemaTheatre,Integer> {
    CinemaTheatre findById(int id);
}
