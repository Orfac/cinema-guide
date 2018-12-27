package ru.kinoguide.entity;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "\"user\"") // Table here is required because User extends BaseEntity
public class User extends BaseEntity {
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Rating> ratingSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Order> orderList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Rating> getRatingSet() {
        return ratingSet;
    }
}
