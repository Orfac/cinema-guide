package ru.kinoguide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kinoguide.entity.User;
import ru.kinoguide.service.RatingService;
import ru.kinoguide.service.UserService;

@Controller
@RequestMapping("/rating")
public class RatingController {

    private UserService userService;

    private RatingService ratingService;

    @Autowired
    public RatingController(UserService userService, RatingService ratingService) {
        this.userService = userService;
        this.ratingService = ratingService;
    }

    @RequestMapping("")
    @Secured({"ROLE_USER"})
    public String getRateByUserId(
            ModelMap model,
            @RequestParam(name = "user", required = false) Integer userId,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "20") Integer ordersOnPage
    ) {
        User user;
        if (userId == null) {
            user = (User) model.get("loggedUser");
        } else {
            user = userService.findOne(userId);
            if (user == null) {
                throw new IllegalArgumentException("User with id " + userId + " has not been found");
            }
        }

        model.put("rates", ratingService.findAllByUser(user, new PageRequest(page, ordersOnPage, Sort.Direction.DESC, "date")));
        return "userRating";
    }

}
