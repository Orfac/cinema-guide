package ru.kinoguide.Services;

import ru.kinoguide.Entity.User;

public class UserRatingCalculator implements RatingCalculator<User> {

    @Override
    public double getProximity(User u1, User u2) {
        // union of sets
        // if number of their commond films < 10 => proximity = undefined (can't count at the moment)
        // S = sum[abs(u2RateForFilm - u1RateForFilm]/N, где N - кол-во общих фильмов
//        Set<Rating> u1RatingSet = new HashSet<>(u1.getRatingSet());
//        u1RatingSet.retainAll(u2.getRatingSet());
//        int[][] Services = new double[u1RatingSet.size()][2];
//        Iterator<Rating> rateIteratior = u1RatingSet.iterator();
//        int i = 0;
//        while (rateIteratior.hasNext()) {
//            Services[i][0] = rateIteratior.next().getRate();
//        }
//        for (Rating rate : u1RatingSet) {
//            Services[]
//        }
//        u2RatingSet.retainAll(u1.getRatingSet());
//        double s = 0;
//        for (Rating u1Rating : u1RatingSet) {
//            s += Services
//        }
//        double s = 2; // 1 <= s <= 10
//        return 100 - 100.0 / 9 * s;
        return 0;
    }
}
