package ru.kinoguide.entity;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "\"user\"") // Table here is required because User extends BaseEntity
public class User extends BaseEntity {
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Логин должен быть указан")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "Пароль должен быть указан")
    private String password;

    @Transient
    @NotEmpty(message = "Повторите пароль")
    private String passwordRepeat;

    @NotBlank(message = "E-mail должен быть указан")
    @Email(message = "Невалидный формат email")
    private String email;

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

    public void setRatingSet(Set<Rating> ratingSet) {
        this.ratingSet = ratingSet;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
