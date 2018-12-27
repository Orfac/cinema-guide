package ru.kinoguide.entity;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"row","column","cinema_hall_id"}))
public class Seat extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "cinema_hall_id", nullable = false) //fetchtype
    private CinemaHall cinemaHall;

    @Column(nullable = false)
    private int row;

    @Column(nullable = false)
    private int column;


}
