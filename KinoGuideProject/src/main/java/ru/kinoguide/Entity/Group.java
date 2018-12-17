package ru.kinoguide.Entity;

import javax.persistence.*;

@Entity
public class Group extends BaseEntity {
    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
