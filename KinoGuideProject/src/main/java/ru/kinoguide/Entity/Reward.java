package ru.kinoguide.Entity;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Reward extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "date", nullable = false, unique = true)
    private Date date;

    @Column(name = "type", nullable = false, unique = true)
    private String type;
}
