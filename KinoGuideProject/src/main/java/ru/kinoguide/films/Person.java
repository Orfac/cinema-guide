package ru.kinoguide.films;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "persons")
@Entity

public class Person {
    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
