package ru.kinoguide.films;



import javax.persistence.*;

@Table(name = "roles")
@Entity
public class Role {
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;
}

