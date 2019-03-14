package ru.kinoguide.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kinoguide.entity.Film;
import ru.kinoguide.entity.Rating;
import ru.kinoguide.entity.User;
import ru.kinoguide.repository.RatingRepository;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class RatingService {

    private RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public Page<Rating> findAllByUser(User user, Pageable pageable) {
        return ratingRepository.findAllByUser(user, pageable);
    }

    public Page<Rating> findDistinctByUserAndFilmGenresIdIn(User user, Integer[] genreIds, Pageable pageable) {
        return ratingRepository.findDistinctRatingsByUserAndFilmGenresIdIn(pageable, user, genreIds);
    }
}
