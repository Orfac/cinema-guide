package ru.kinoguide.rating;

import ru.kinoguide.BaseEntity;
import ru.kinoguide.films.Film;
import ru.kinoguide.user.User;

import javax.persistence.*;

@Entity
@Table(name = "user_films_rating")
public class Rating extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private int rate;
}
