package ru.kinoguide.order;

import ru.kinoguide.BaseEntity;
import ru.kinoguide.user.User;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

// todo чекни
@Table(name = "tickets")
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
