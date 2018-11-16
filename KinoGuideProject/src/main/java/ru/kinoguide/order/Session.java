package ru.kinoguide.order;

import ru.kinoguide.films.Film;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sessions")
public class Session {


    @Column(nullable = false)
    private Date startTime;

    @Column(nullable = false)
    private Date endTime;

    @ManyToOne
    @JoinColumn(name = "cinema_hall_id", nullable = false)
    private CinemaHall cinemaHall;

    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;
}
