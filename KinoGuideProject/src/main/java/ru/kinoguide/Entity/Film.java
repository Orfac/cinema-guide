package ru.kinoguide.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.Instant;
import java.util.Set;

@Entity
public class Film extends BaseEntity {

    @Column(name = "name", nullable = false, unique = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String info;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "film")
    private Set<Rating> ratingSet;

    @ManyToMany(cascade = CascadeType.ALL) // TODO Саня решить вопрос с каскадностью
    @JoinTable(name = "films_genres", joinColumns =
    @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genreSet;

    @Column(name = "date_shoot_start", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "UTC")
    private Instant dateShootingStart;

    @Column(name = "date_shoot_end")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "UTC")
    private Instant dateShootingEnd;

    @Column(name = "date_premiere")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "UTC")
    private Instant datePremiere;

    @Column(name = "duration", nullable = false)
    @Min(1)
    private int duration;

    @Column(name = "annotation", nullable = false)
    private String annotation;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "ageRating")
    private String ageRating;

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }
}
