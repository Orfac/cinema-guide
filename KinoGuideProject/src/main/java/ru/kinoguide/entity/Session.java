package ru.kinoguide.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
public class Session extends BaseEntity {

    @Column(name = "start_time", nullable = false)
    private Instant startTime;

    @Column(name = "end_time", nullable = false)
    private Instant endTime;

    @ManyToOne
    @JoinColumn(name = "cinema_hall_id", nullable = false)
    private CinemaHall cinemaHall;

    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "session", orphanRemoval = true)
    @OrderBy("price ASC")
    private List<Ticket> tickets;

    public Session() {
    }

    public Session(Instant startTime, Instant endTime, CinemaHall cinemaHall, Film film) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.cinemaHall = cinemaHall;
        this.film = film;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(CinemaHall cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Ticket getTicketWithMinPrice() {
        if (tickets.size() > 0) {
            return tickets.get(0);
        } else {
            return null;
        }
    }
}
