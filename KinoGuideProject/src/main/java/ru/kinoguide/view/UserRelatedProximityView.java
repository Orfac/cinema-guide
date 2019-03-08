package ru.kinoguide.view;

import ru.kinoguide.entity.Film;
import ru.kinoguide.entity.User;
import ru.kinoguide.entity.UsersRatingProximity;

public class UserRelatedProximityView {

    private UsersRatingProximity usersRatingProximity;

    private Film film;

    public UserRelatedProximityView(UsersRatingProximity usersRatingProximity, Film film) {
        this.usersRatingProximity = usersRatingProximity;
        this.film = film;
    }

    public User getProximityFriend() {
        return usersRatingProximity.getUser2();
    }

    public UsersRatingProximity getUsersRatingProximity() {
        return usersRatingProximity;
    }

    public void setUsersRatingProximity(UsersRatingProximity usersRatingProximity) {
        this.usersRatingProximity = usersRatingProximity;
    }

    public int getProximity() {
        return (int) Math.round(usersRatingProximity.getProximity());
    }

    public User getUser() {
        return usersRatingProximity.getUser1();
    }

    public Film getFilm() {
        return film;
    }

    public int getRate() {
        return getProximityFriend().getRatingByFilm(film).getRate();
    }
}
