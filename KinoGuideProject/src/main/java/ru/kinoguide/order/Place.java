package ru.kinoguide.order;

import ru.kinoguide.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "places", uniqueConstraints = @UniqueConstraint(columnNames = {"row","column","cinema_hall_id"}))
public class Place extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "cinema_hall_id", nullable = false)
    private CinemaHall cinemaHall;

    @Column(nullable = false)
    private int row;

    @Column(nullable = false)
    private int column;


}
