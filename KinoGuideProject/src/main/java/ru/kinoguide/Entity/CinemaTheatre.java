package ru.kinoguide.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class CinemaTheatre extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String site;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cinemaTheatre")
    private Set<CinemaHall> cinemaHalls;

    @ManyToOne
    @JoinColumn(name = "network_id", nullable = false)
    private CinemaNetwork cinemaNetwork;
}
