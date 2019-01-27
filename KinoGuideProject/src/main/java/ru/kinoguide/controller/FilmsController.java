package ru.kinoguide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kinoguide.entity.Film;
import ru.kinoguide.repository.FilmRepository;

import java.time.Instant;


@Controller
@RequestMapping("/film")
public class FilmsController {

    @Autowired
    private FilmRepository filmRepository;

    @RequestMapping("billboard")
    public String billboard(
            ModelMap model,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "20") Integer filmsOnPage

    ) {
        model.put("films", filmRepository.findFilmsWhichHaveSessionsSinceNow(new PageRequest(page, filmsOnPage, Sort.Direction.DESC, "datePremiere")));
        return "billboard";
    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String film(
            Model model,
            @RequestParam(name = "id") Integer id,
            @RequestParam(name = "date", required = false) Instant sessionsDate

    ) {
        Film film = filmRepository.findOne(id);
        if (film == null) {
            model.addAttribute("error", "Фильм не найден");
            return "error";
        }
        model.addAttribute("film", film);
        return "film";
    }
}
