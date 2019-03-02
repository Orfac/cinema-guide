package ru.kinoguide.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "\"group\"")
public class Group extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    private Set<FilmRole> filmRoleSet;

    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<FilmRole> getFilmRoleSet() {
        return filmRoleSet;
    }

    public void setFilmRoleSet(Set<FilmRole> filmRoleSet) {
        this.filmRoleSet = filmRoleSet;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
