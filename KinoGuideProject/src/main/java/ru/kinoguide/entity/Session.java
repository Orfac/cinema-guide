package ru.kinoguide.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "session")
    private Set<Ticket> tickets;
}
