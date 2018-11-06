package ru.kinoguide.rating;

import ru.kinoguide.user.User;

public class RatingCalculator implements IRatingCalculator {

    @Override
    public double getProximityBetweenUsers(User u1, User u2) {
        // union of sets
        // if number of their commond films < 10 => proximity = undefined (can't count at the moment)
        // S = sum[abs(u2RateForFilm - u1RateForFilm]/N, где N - кол-во общих фильмов
        double s = 2; // 1 <= s <= 10
        return 100 - 100.0/9 * s;
    }
}
