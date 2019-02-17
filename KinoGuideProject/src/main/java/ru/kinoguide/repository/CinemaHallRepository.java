package ru.kinoguide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.kinoguide.entity.CinemaHall;

public interface CinemaHallRepository extends JpaRepository<CinemaHall, Integer> {
}
