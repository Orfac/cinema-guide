package ru.kinoguide.entity;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"row","column","cinema_hall_id"}))
public class Seat extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "cinema_hall_id", nullable = false) //fetchtype
    private CinemaHall cinemaHall;

    @Column(name = "row", nullable = false)
    private int row;

    @Column(name = "\"column\"", nullable = false)
    private int column;


}
