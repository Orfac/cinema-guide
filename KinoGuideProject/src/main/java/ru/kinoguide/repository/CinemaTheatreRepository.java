package ru.kinoguide.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.kinoguide.entity.CinemaTheatre;

import java.util.List;

public interface CinemaTheatreRepository extends PagingAndSortingRepository<CinemaTheatre,Integer> {
    CinemaTheatre findById(int id);
    List<CinemaTheatre> findAll();
}
