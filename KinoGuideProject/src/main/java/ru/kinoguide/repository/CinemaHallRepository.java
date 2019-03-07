package ru.kinoguide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kinoguide.entity.CinemaHall;
import ru.kinoguide.entity.CinemaNetwork;

public interface CinemaHallRepository extends JpaRepository<CinemaHall, Integer> {

    CinemaHall findOneByNumberAndCinemaTheatreCinemaNetwork(CinemaNetwork cinemaNetwork, int number);
}
