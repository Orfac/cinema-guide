package ru.kinoguide.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Media extends BaseEntity {
    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "type", nullable = false)
    private String type;

    @ManyToOne
    @JoinColumn(name = "cinema_network_id")
    private CinemaNetwork cinemaNetwork;

    @ManyToOne
    @JoinColumn(name = "cinema_theatre_id")
    private CinemaTheatre cinemaTheatre;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CinemaNetwork getCinemaNetwork() {
        return cinemaNetwork;
    }

    public void setCinemaNetwork(CinemaNetwork cinemaNetwork) {
        this.cinemaNetwork = cinemaNetwork;
    }

    public CinemaTheatre getCinemaTheatre() {
        return cinemaTheatre;
    }

    public void setCinemaTheatre(CinemaTheatre cinemaTheatre) {
        this.cinemaTheatre = cinemaTheatre;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
