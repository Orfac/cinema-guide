package ru.kinoguide.order;

import ru.kinoguide.BaseEntity;
import ru.kinoguide.films.Film;

import javax.persistence.*;
import java.util.Date;

// todo нужно ли что-нибудь изменять?
@Table(name = "sessions")
@Entity
public class Session  extends BaseEntity {

    @Column(name = "start_time", nullable = false)
    private Date startTime;

    @Column(name = "end_time", nullable = false)
    private Date endTime;

    @ManyToOne
    @JoinColumn(name = "cinema_hall_id", nullable = false)
    private CinemaHall cinemaHall;

    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;
}
