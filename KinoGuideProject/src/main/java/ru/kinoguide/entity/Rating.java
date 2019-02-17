package ru.kinoguide.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.Instant;

@Entity
public class Rating extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    @Range(min = 1, max = 10)
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

    public void setFilm(Film film) {
        this.film = film;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setDate(Instant date) {
        this.date = date;
    }
}
