package ru.kinoguide.entity;

import javax.persistence.*;

@Entity
public class Group extends BaseEntity {
    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
