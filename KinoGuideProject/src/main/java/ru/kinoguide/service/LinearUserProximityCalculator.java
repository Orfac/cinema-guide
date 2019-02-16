package ru.kinoguide.service;

import org.springframework.stereotype.Service;
import ru.kinoguide.entity.Rating;

import java.util.List;

@Service("linearUserProximityCalculator")
public class LinearUserProximityCalculator implements UserProximityCalculator {

    private final static int COMMON_RATING_THRESHOLD = 10;

    public double calculateProximityByCommonRatings(List<Object[]> commonRatings) {
        if (commonRatings.size() < COMMON_RATING_THRESHOLD) {
            return 0;
        }
        double s = commonRatings.stream().mapToInt(ratingList -> {
            return Math.abs(((Rating)ratingList[0]).getRate() - ((Rating)ratingList[1]).getRate());
        }).sum();
        s /= commonRatings.size();
        return 100.0 - s * 100.0 / 9.0;
    }
}

