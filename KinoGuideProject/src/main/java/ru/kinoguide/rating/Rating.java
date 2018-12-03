package ru.kinoguide.rating;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import ru.kinoguide.BaseEntity;
import ru.kinoguide.films.Film;
import ru.kinoguide.user.User;

import javax.persistence.*;
import java.time.Instant;

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

    @Column(name = "date", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "UTC")
    private Instant date;

    /**
     * For user proximity calculation
     *
     * @return
     */
    @Override
    public int hashCode() {
        return film.hashCode();
    }

    public Film getFilm() {
        return film;
    }

    public User getUser() {
        return user;
    }

    public int getRate() {
        return rate;
    }

    public Instant getDate() {
        return date;
    }
}
