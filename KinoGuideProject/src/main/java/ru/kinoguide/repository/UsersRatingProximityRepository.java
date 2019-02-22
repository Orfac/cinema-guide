package ru.kinoguide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kinoguide.entity.Film;
import ru.kinoguide.entity.User;
import ru.kinoguide.entity.UsersRatingProximity;

import java.util.List;

public interface UsersRatingProximityRepository extends JpaRepository<UsersRatingProximity, Integer> {
    @Query("SELECT p FROM UsersRatingProximity p, Rating r WHERE p.user1 = :user AND r.film = :film AND r.user = p.user2 ORDER BY p.proximity DESC")
//    @Query(value = "SELECT p FROM UsersRatingProximity p JOIN p.user2 u ON (p.user2 = u) JOIN p.user2.ratingSet r ON (r.user = p.user2 AND r.film = :film) WHERE p.user1 = :user ORDER BY p.proximity ASC")
    List<UsersRatingProximity> getMostProximityUsersByFilmAndUser(@Param("user") User user, @Param("film") Film film);
}
