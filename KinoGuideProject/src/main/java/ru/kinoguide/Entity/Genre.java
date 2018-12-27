package ru.kinoguide.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Genre extends BaseEntity {
    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
