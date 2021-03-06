package ru.kinoguide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kinoguide.repository.RatingRepository;

@Controller
@RequestMapping("/rating")
public class RatingController {

    // TODO добавить filmRepository
    @Autowired
    private RatingRepository ratingRepository;

    // TODO добавить User и pageableRequest в findAll
    @RequestMapping("/{userId}")
    public String getUserRating(ModelMap model) {
        model.put("rates", ratingRepository.findAll());
        return "userRating";
    }

    // TODO SetRating для изменения оценки {Post}
    // TODO {придумать название метода} получает массив схожих юзеров и их оценки и их схожесть
}
