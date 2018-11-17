package ru.kinoguide.films;

import ru.kinoguide.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "persons")
@Entity
public class Person extends BaseEntity {
    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
