package ru.kinoguide.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kinoguide.entity.Rating;
import ru.kinoguide.entity.User;
import ru.kinoguide.repository.RatingRepository;

import java.util.List;

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

}
