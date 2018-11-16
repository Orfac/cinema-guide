package ru.kinoguide.order;

import ru.kinoguide.BaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "cinema_halls")
public class Ticket extends BaseEntity {


    @Column(nullable = false)
    private int price;

    // todo Исправить mapping
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "")
    private Set<Seat> seat;
}
