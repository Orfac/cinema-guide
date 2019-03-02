package ru.kinoguide.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Person extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private Set<FilmRole> filmRoleSet;

    public Person(String name) {
        this.name = name;
    }

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
}
