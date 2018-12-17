package ru.kinoguide.Entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class CinemaHall extends BaseEntity {
    @Column(nullable = false, unique = true)
    private int number;

    @ManyToOne
    @JoinColumn(name = "cinema_theatre_id", nullable = false)
    private CinemaTheatre cinemaTheatre;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cinemaHall")
    private Set<Seat> seats;
}
