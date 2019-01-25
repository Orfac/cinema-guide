package ru.kinoguide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kinoguide.repository.FilmRepository;

@Controller
@RequestMapping("/film")
public class FilmsController {

    @Autowired
    private FilmRepository filmRepository;

    @RequestMapping("/billboard")
    public String index(ModelMap model) {
        model.put("films", filmRepository.findAll());
        return "billboard";
    }

}
