package ru.kinoguide.Services;

import ru.kinoguide.Entity.User;

public interface IRatingCalculator {
    public double getProximityBetweenUsers(User u1, User u2);
}
