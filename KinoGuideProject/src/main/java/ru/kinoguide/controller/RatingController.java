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
import ru.kinoguide.entity.User;
import ru.kinoguide.service.GenreService;
import ru.kinoguide.service.RatingService;
import ru.kinoguide.service.UserService;

@Controller
@RequestMapping("/rating")
public class RatingController {

    private UserService userService;

    private RatingService ratingService;

    private GenreService genreService;

    @Autowired
    public RatingController(UserService userService, RatingService ratingService, GenreService genreService) {
        this.userService = userService;
        this.ratingService = ratingService;
        this.genreService = genreService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @Secured({"ROLE_USER"})
    public String getRateByUserId(
            ModelMap model,
            @RequestParam(name = "user", required = false) Integer userId,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "ordersOnPage", required = false, defaultValue = "12") Integer ordersOnPage,
            @RequestParam(name = "sortDirectionDesc", required = false, defaultValue = "false") boolean isSortDirectionDesc,
            @RequestParam(name = "sortField", required = false, defaultValue = "date") String sortField,
            @RequestParam(name = "genres", required = false) Integer[] genreIds
    ) {
        if (genreIds == null || genreIds.length == 0) {
            genreIds = genreService.findAll().stream().map(genre -> genre.getId()).toArray(Integer[]::new);
        }

        User user;
        if (userId == null) {
            user = (User) model.get("loggedUser");
        } else {
            user = userService.findOne(userId);
            if (user == null) {
                throw new IllegalArgumentException("User with id " + userId + " has not been found");
            }
        }

        model.put("rates", ratingService.findDistinctByUserAndFilmGenresIdIn(user, genreIds, new PageRequest(
                page, ordersOnPage, isSortDirectionDesc ? Sort.Direction.DESC : Sort.Direction.ASC, sortField)));
        return "userRating";
    }
}
