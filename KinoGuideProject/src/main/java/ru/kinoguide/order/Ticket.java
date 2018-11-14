package ru.kinoguide.order;

import ru.kinoguide.BaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "cinema_halls")
public class Ticket extends BaseEntity {
    @Column(nullable = false)
    private Date startTime;

    @Column(nullable = false)
    private Date endTime;

    @Column(nullable = false)
    private int price;

    @ManyToOne
    @JoinColumn(name = "cinema_theatre_id", nullable = false)
    private CinemaTheatre cinemaTheatre;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cinemaHall")
    private Set<Seat> seats;
}
