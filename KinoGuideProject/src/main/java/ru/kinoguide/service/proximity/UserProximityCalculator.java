package ru.kinoguide.service.proximity;

import ru.kinoguide.entity.Rating;

import java.util.List;

public interface UserProximityCalculator {
    double calculateProximityByCommonRatings(List<Object[]> commonRatings);
}
