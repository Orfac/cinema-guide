package ru.kinoguide.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.kinoguide.entity.Rating;
import ru.kinoguide.entity.User;

@Transactional(readOnly = true)

public interface RatingRepository extends PagingAndSortingRepository<Rating, Integer> {

    Page<Rating> findAllByUser(User user, Pageable pageable);

    @Query("SELECT r1, r2 FROM Rating r1 INNER JOIN Rating r2 ON r1.film = r2.film AND r1.user = u1 AND r2.user = u2")
    Page<Rating> findCommonRates( User u1, User u2, Pageable pageable);
}
