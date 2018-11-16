package ru.kinoguide.films;

import javax.persistence.*;

@Table(name = "groups")
@Entity
public class Group {
    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
