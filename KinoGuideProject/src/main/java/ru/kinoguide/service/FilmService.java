package ru.kinoguide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kinoguide.entity.Film;
import ru.kinoguide.repository.FilmRepository;
import ru.kinoguide.repository.UsersRatingProximityRepository;

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

    public Page<Film> findFilmsWhichHaveSessionsSinceNow(Pageable pageable) {
        return filmRepository.findFilmsWhichHaveSessionsSinceNow(pageable);
    }
}
