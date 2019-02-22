package ru.kinoguide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kinoguide.entity.Film;
import ru.kinoguide.entity.User;
import ru.kinoguide.entity.UsersRatingProximity;
import ru.kinoguide.repository.FilmRepository;
import ru.kinoguide.repository.UsersRatingProximityRepository;
import ru.kinoguide.view.UserRelatedProximity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/film")
public class FilmsController {

    private static final int TOP_PROXIMITY_USER_RATES = 5;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private UsersRatingProximityRepository usersRatingProximityRepository;

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
            ModelMap modelMap,
            @RequestParam(name = "id") Integer id,
            @RequestParam(name = "date", required = false) Instant sessionsDate

    ) {
        Film film = filmRepository.findOne(id);
        if (film == null) {
            modelMap.addAttribute("error", "Фильм не найден");
            return "error";
        }
        User loggedUser = (User) modelMap.get("loggedUser");
        if (loggedUser != null) {
            List<UserRelatedProximity> userRelatedProximityList = new ArrayList<>();
            List<UsersRatingProximity> usersRatingProximityList = usersRatingProximityRepository.getMostProximityUsersByFilmAndUser(loggedUser, film);
            for (int i = 0; i < usersRatingProximityList.size() && i < TOP_PROXIMITY_USER_RATES; i++) {
                UsersRatingProximity usersRatingProximity = usersRatingProximityList.get(i);
                UserRelatedProximity userRelatedProximity = new UserRelatedProximity(usersRatingProximity.getUser2(), usersRatingProximity, film);
                userRelatedProximityList.add(userRelatedProximity);
            }
            modelMap.addAttribute("closeUserRelatedProximityList", userRelatedProximityList);
        }
        modelMap.addAttribute("film", film);
        return "film";
    }
}
