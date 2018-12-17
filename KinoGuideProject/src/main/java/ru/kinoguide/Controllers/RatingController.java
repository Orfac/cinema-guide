package ru.kinoguide.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kinoguide.Repositories.RatingRepository;

@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingRepository ratingRepository;

    @RequestMapping("/{userId}")
    public String getUserRating(ModelMap model) {
        model.put("rates", ratingRepository.findAll());
        return "userRating";
    }
}
