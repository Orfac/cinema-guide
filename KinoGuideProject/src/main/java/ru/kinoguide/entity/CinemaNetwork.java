package ru.kinoguide.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class CinemaNetwork extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cinemaNetwork")
    private Set<CinemaTheatre> cinemaTheatreSet;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CinemaTheatre> getCinemaTheatreSet() {
        return cinemaTheatreSet;
    }

    public void setCinemaTheatreSet(Set<CinemaTheatre> cinemaTheatreSet) {
        this.cinemaTheatreSet = cinemaTheatreSet;
    }
}
