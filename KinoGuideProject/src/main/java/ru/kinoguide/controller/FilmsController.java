package ru.kinoguide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kinoguide.entity.Film;
import ru.kinoguide.repository.FilmRepository;

import java.util.Date;

@Controller
@RequestMapping("/film")
public class FilmsController {

    @Autowired
    private FilmRepository filmRepository;

    @RequestMapping("billboard")
    public String index(ModelMap model) {
        model.put("films", filmRepository.findAll());
        return "billboard";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String index(ModelMap model, @PathVariable("id") int id,
                        @RequestParam(required=false) @DateTimeFormat(pattern="MMddyyyy") Date day){
        Film film = filmRepository.findById(id);

        return "film";
    }

}
