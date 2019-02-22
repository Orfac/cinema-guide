package ru.kinoguide.view;

import ru.kinoguide.entity.Film;
import ru.kinoguide.entity.User;
import ru.kinoguide.entity.UsersRatingProximity;

public class UserRelatedProximity {

    private User user;

    private UsersRatingProximity usersRatingProximity;

    private Film film;

    public UserRelatedProximity(User user, UsersRatingProximity usersRatingProximity, Film film) {
        this.user = user;
        this.usersRatingProximity = usersRatingProximity;
        this.film = film;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UsersRatingProximity getUsersRatingProximity() {
        return usersRatingProximity;
    }

    public void setUsersRatingProximity(UsersRatingProximity usersRatingProximity) {
        this.usersRatingProximity = usersRatingProximity;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public int getRate() {
        return user.getRatingByFilm(film).getRate();
    }

    public double getProximity() {
        return usersRatingProximity.getProximity();
    }
}
