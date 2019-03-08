package ru.kinoguide.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.kinoguide.entity.CinemaTheatre;
import ru.kinoguide.entity.Film;
import ru.kinoguide.entity.Session;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface SessionRepository extends PagingAndSortingRepository<Session, Integer> {
    @Query("select s from Session s where s.startTime > CURRENT_DATE")
    Page<Session> findAllSinceNow(Pageable pageable);

    @Query("select s from Session s where s.startTime > instant")
    Page<Session> findAllSinceDate(Pageable pageable, Instant instant);

    @Query("select s from Session s where s.film.name in (filmsNames)")
    List<Session> findSessionsByFilms(String[] filmsNames);

    Page<Session> findByFilmAndStartTimeBetweenOrderByStartTimeAsc(Pageable pageable, Film film, Instant start, Instant end);

    List<Session> findByFilmAndStartTimeBetweenOrderByStartTimeAsc(Film film, Instant start, Instant end);

    List<Session> findByCinemaHallCinemaTheatreAndStartTimeBetweenOrderByStartTimeAsc(CinemaTheatre cinemaTheatre, Instant start, Instant end);
    @Query("select s from Session s where s.startTime > CURRENT_DATE")
    List<Session> findAllSinceNow();

}
