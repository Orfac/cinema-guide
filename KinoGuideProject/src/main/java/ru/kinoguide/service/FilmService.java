package ru.kinoguide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kinoguide.entity.Film;
import ru.kinoguide.repository.FilmRepository;
import ru.kinoguide.repository.UsersRatingProximityRepository;

import java.time.Instant;
import java.util.List;

@Service
@Transactional
public class FilmService {

    private FilmRepository filmRepository;

    private UsersRatingProximityRepository usersRatingProximityRepository;

    @Autowired
    public FilmService(FilmRepository filmRepository, UsersRatingProximityRepository usersRatingProximityRepository) {
        this.filmRepository = filmRepository;
        this.usersRatingProximityRepository = usersRatingProximityRepository;
    }

    public Film findOne(Integer filmId) {
        return filmRepository.findOne(filmId);
    }


    public List<Film> findAllBySessionStartTimeAfterAndGenreIdIn(Instant instant, Integer[] genreIds) {
        return filmRepository.findAllDistinctFilmsBySessionsStartTimeAfterAndGenresIdIn(instant, genreIds);
    }

    public Page<Film> findBySessionStartTimeAfterAndGenreIdIn(Pageable pageable, Instant instant, Integer[] genreIds) {
        return filmRepository.findDistinctFilmsBySessionsStartTimeAfterAndGenresIdIn(pageable, instant, genreIds);
    }

    public List<Film> findAllDistinctFilmsBySessionsStartTimeAfter(Instant instant) {
        return filmRepository.findAllDistinctFilmsBySessionsStartTimeAfter(instant);
    }

    public List<Film> findFilmsWhichHaveSessionsSinceNow() {
        return findAllDistinctFilmsBySessionsStartTimeAfter(Instant.now());
    }
}
