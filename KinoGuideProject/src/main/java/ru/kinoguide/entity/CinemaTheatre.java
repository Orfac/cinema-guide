package ru.kinoguide.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class CinemaTheatre extends DisplayableEntity {

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cinemaTheatre", orphanRemoval = true)
    private Set<CinemaHall> cinemaHalls;

    @ManyToOne
    @JoinColumn(name = "network_id", nullable = false)
    private CinemaNetwork cinemaNetwork;

    public CinemaNetwork getCinemaNetwork() {
        return cinemaNetwork;
    }

    public void setCinemaNetwork(CinemaNetwork cinemaNetwork) {
        this.cinemaNetwork = cinemaNetwork;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<CinemaHall> getCinemaHalls() {
        return cinemaHalls;
    }

    public void setCinemaHalls(Set<CinemaHall> cinemaHalls) {
        this.cinemaHalls = cinemaHalls;
    }
}
