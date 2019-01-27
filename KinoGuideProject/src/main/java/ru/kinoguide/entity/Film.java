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

    @Column(columnDefinition = "TEXT", nullable = false)
    private String info;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "film")
    private Set<Group> groupSet;

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

    @Column(name = "ageRating", nullable = false)
    private String ageRating;

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Set<Rating> getRatingSet() {
        return ratingSet;
    }

    public void setRatingSet(Set<Rating> ratingSet) {
        this.ratingSet = ratingSet;
    }

    public Set<Genre> getGenreSet() {
        return genreSet;
    }

    public void setGenreSet(Set<Genre> genreSet) {
        this.genreSet = genreSet;
    }

    public Instant getDateShootingStart() {
        return dateShootingStart;
    }

    public void setDateShootingStart(Instant dateShootingStart) {
        this.dateShootingStart = dateShootingStart;
    }

    public Instant getDateShootingEnd() {
        return dateShootingEnd;
    }

    public void setDateShootingEnd(Instant dateShootingEnd) {
        this.dateShootingEnd = dateShootingEnd;
    }

    public Instant getDatePremiere() {
        return datePremiere;
    }

    public void setDatePremiere(Instant datePremiere) {
        this.datePremiere = datePremiere;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(String ageRating) {
        this.ageRating = ageRating;
    }

    public Set<Group> getGroupSet() {
        return groupSet;
    }

    public void setGroupSet(Set<Group> groupSet) {
        this.groupSet = groupSet;
    }
}
