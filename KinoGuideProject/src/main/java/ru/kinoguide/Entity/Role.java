package ru.kinoguide.Entity;


import javax.persistence.*;

@Table(name = "roles")
@Entity
public class Role extends BaseEntity {
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;
}

