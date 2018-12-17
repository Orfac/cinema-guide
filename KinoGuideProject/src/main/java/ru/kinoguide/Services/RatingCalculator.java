package ru.kinoguide.Services;

import ru.kinoguide.Entity.User;

public interface RatingCalculator <E> {
    public double getProximity(E u1, E u2);
}
