package ru.kinoguide.Entity;

import javax.persistence.*;

@Table(name = "groups")
@Entity
public class Group extends BaseEntity {
    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
