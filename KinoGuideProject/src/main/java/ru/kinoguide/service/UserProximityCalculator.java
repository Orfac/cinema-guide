package ru.kinoguide.service;

import ru.kinoguide.entity.User;

public interface UserProximityCalculator {
    public double calculateProximity(User u1, User u2);
}
