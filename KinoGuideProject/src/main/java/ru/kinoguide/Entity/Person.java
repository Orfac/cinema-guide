package ru.kinoguide.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class Person extends BaseEntity {
    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
