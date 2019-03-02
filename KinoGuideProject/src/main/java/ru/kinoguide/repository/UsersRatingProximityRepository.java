package ru.kinoguide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kinoguide.entity.Film;
import ru.kinoguide.entity.User;
import ru.kinoguide.entity.UsersRatingProximity;

import java.util.List;

public interface UsersRatingProximityRepository extends JpaRepository<UsersRatingProximity, Integer> {
    @Query(value = "SELECT u.* FROM users_rating_proximity AS u JOIN rating AS r ON (r.user_id = u.user2_id AND r.film_id=:film) WHERE u.user1_id=:user ORDER BY u.proximity DESC LIMIT :resultsNumber", nativeQuery = true)
    List<UsersRatingProximity> getUsersWithClosestProximityByFilm(@Param("user") int userId, @Param("film") int filmId, @Param("resultsNumber") int resultsNumber);
}
