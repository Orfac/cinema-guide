package ru.kinoguide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kinoguide.entity.Film;
import ru.kinoguide.entity.User;
import ru.kinoguide.service.FilmService;
import ru.kinoguide.service.proximity.ProximityService;

import java.time.Instant;


@Controller
@RequestMapping("/film")
public class FilmsController {

    private static final int TOP_PROXIMITY_USER_RATES = 5;

    private FilmService filmService;

    private ProximityService proximityService;

    @Autowired
    public FilmsController(FilmService filmService, ProximityService proximityService) {
        this.filmService = filmService;
        this.proximityService = proximityService;
    }

    @RequestMapping("billboard")
    public String billboard(
            ModelMap model,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "20") Integer filmsOnPage

    ) {
        model.put("films", filmService.findFilmsWhichHaveSessionsSinceNow(new PageRequest(page, filmsOnPage, Sort.Direction.DESC, "datePremiere")));
        return "billboard";
    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String film(
            ModelMap modelMap,
            @RequestParam(name = "id") Integer id,
            @RequestParam(name = "date", required = false) Instant sessionsDate

    ) {
        Film film = filmService.findOne(id);
        if (film == null) {
            modelMap.addAttribute("error", "Фильм не найден");
            return "error";
        }
        User loggedUser = (User) modelMap.get("loggedUser");
        if (loggedUser != null) {
            modelMap.addAttribute("closeUserRelatedProximityList", proximityService.getUsersWithClosestProximityByFilm(loggedUser, film, TOP_PROXIMITY_USER_RATES));
        }
        modelMap.addAttribute("film", film);
        return "film";
    }
}
