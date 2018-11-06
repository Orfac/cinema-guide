package ru.kinoguide.order;

import ru.kinoguide.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cinema_networks")
public class CinemaNetwork extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cinemaNetwork")
    private Set<CinemaTheatre> cinemaTheatreSet;
}
