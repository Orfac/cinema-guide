package ru.kinoguide.entity;

import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"user1_id", "user2_id"}))
public class UsersRatingProximity extends BaseEntity {
    @ManyToOne(optional = false)
    private User user1;

    @ManyToOne(optional = false)
    private User user2;

    @Range(min = 0, max = 100)
    @NotNull
    private double proximity;

    public UsersRatingProximity() {

    }


    public UsersRatingProximity(User user1, User user2, double proximity) {
        this.user1 = user1;
        this.user2 = user2;
        this.proximity = proximity;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public double getProximity() {
        return proximity;
    }

    public void setProximity(double proximity) {
        this.proximity = proximity;
    }
}
