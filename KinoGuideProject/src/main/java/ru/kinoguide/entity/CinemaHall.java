package ru.kinoguide.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
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
    private List<Seat> seats;

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

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CinemaHall that = (CinemaHall) o;
        return number == that.number &&
                Objects.equals(cinemaTheatre, that.cinemaTheatre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, cinemaTheatre);
    }
}
