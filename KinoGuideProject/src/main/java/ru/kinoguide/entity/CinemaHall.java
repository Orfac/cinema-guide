package ru.kinoguide.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"number", "cinema_theatre_id"}))
public class CinemaHall extends BaseEntity {
    @Column(nullable = false)
    private int number;

    @ManyToOne
    @JoinColumn(name = "cinema_theatre_id", nullable = false)
    private CinemaTheatre cinemaTheatre;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cinemaHall", orphanRemoval = true)
    private Set<Seat> seats;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public CinemaTheatre getCinemaTheatre() {
        return cinemaTheatre;
    }

    public void setCinemaTheatre(CinemaTheatre cinemaTheatre) {
        this.cinemaTheatre = cinemaTheatre;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }
}
