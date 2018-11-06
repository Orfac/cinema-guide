package ru.kinoguide.films;

import ru.kinoguide.BaseEntity;

import javax.persistence.*;

@Table(name = "films")
@Entity
public class Film extends BaseEntity {

    @Column(name = "name", nullable = false, unique = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String info;

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }
}
