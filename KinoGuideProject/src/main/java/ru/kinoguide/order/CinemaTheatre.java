package ru.kinoguide.order;

import ru.kinoguide.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "cinema_theatres")
public class CinemaTheatre extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String site;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name = "network_id", nullable = false)
    private CinemaNetwork cinemaNetwork;
}
