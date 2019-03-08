package ru.kinoguide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kinoguide.entity.CinemaTheatre;
import ru.kinoguide.entity.Film;
import ru.kinoguide.entity.Session;
import ru.kinoguide.repository.SessionRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@Transactional
public class SessionService {

    private SessionRepository sessionRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public Session addSession(Session session) {
        return sessionRepository.save(session);
    }

    public List<Session> findRelevant(String[] films, LocalDate[] dates,
                                      LocalTime[] leftTimes, LocalTime[] rightTimes,
                                      int[] Prices) {

        // TODO время в формате ISO с учётом часового пояса пользователя
        return null;
    }

    /**
     *
     * @param pageable
     * @param film
     * @param start
     * @param end
     * @return sorted by start time ascending
     */
    public Page<Session> findByFilmAndStartTimeBetween(Pageable pageable, Film film, Instant start, Instant end) {
        return sessionRepository.findByFilmAndStartTimeBetweenOrderByStartTimeAsc(pageable, film, start, end);
    }

    public List<Session> findByFilmAndStartTimeBetweenOrderByStartTimeAsc(Film film, Instant start, Instant end) {
        return sessionRepository.findByFilmAndStartTimeBetweenOrderByStartTimeAsc(film, start, end);
    }


    public List<Session> findByCinemaHallCinemaTheatreAndStartTimeBetweenOrderByStartTimeAsc(CinemaTheatre cinemaTheatre, Instant start, Instant end) {
        return sessionRepository.findByCinemaHallCinemaTheatreAndStartTimeBetweenOrderByStartTimeAsc(cinemaTheatre, start, end);
    }
}
