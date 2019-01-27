package ru.kinoguide.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Genre extends BaseEntity {
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
