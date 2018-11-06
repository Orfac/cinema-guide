package ru.kinoguide.films;

import ru.kinoguide.BaseEntity;
import ru.kinoguide.rating.Rating;
import ru.kinoguide.user.User;

import javax.persistence.*;
import java.util.Set;

@Table(name = "films")
@Entity
public class Film extends BaseEntity {

    @Column(name = "name", nullable = false, unique = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String info;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "film")
    private Set<Rating> ratingSet;

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }
}
