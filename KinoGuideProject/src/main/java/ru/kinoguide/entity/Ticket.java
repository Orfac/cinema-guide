package ru.kinoguide.entity;

import javax.persistence.*;


@Entity
public class Ticket extends BaseEntity {


    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;

}
