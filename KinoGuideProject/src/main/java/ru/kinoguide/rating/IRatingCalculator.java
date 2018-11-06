package ru.kinoguide.rating;

import ru.kinoguide.user.User;

public interface IRatingCalculator {
    public double getProximityBetweenUsers(User u1, User u2);
}
