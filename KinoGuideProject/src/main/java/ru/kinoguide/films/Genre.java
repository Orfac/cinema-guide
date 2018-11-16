package ru.kinoguide.films;

import ru.kinoguide.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "genres")
public class Genre extends BaseEntity {
    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
