package ru.kinoguide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kinoguide.entity.Film;
import ru.kinoguide.entity.Session;
import ru.kinoguide.entity.User;
import ru.kinoguide.repository.FilmRepository;
import ru.kinoguide.repository.OrderRepository;
import ru.kinoguide.repository.UserRepository;
import ru.kinoguide.service.OrderService;
import ru.kinoguide.service.SessionService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;
    private SessionService sessionService;

    @Autowired
    public OrderController(OrderService orderService, SessionService sessionService) {
        this.orderService = orderService;
        this.sessionService = sessionService;
    }

    @Autowired
    private FilmRepository filmRepository;

    @RequestMapping("")
    @Secured({"ROLE_USER"})
    public String getLoggedUserOrders(
            ModelMap modelMap,
            @RequestParam(name = "user", required = false) Integer userId,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "20") Integer ordersOnPage
    ) {
        User loggedUser = (User) modelMap.get("loggedUser");

        if (userId != null && !userId.equals(loggedUser.getId()) && !loggedUser.hasRole("ROLE_ADMIN")) {
            throw new AccessDeniedException("Not enough permissions to get user's orders");
        } else if (userId == null) {
            userId = loggedUser.getId();
        }

        modelMap.addAttribute("orders", orderService.findAllByUserId(userId, new PageRequest(page, ordersOnPage, Sort.Direction.DESC, "dateCreated")));
        return "userOrders";
    }

    @RequestMapping("auto")
    public String getAuto(ModelMap model){
        List<Film> filmList = filmRepository.findFilmsWhichHaveSessionsSinceNow();
        List<String> filmNamesList = filmList.stream().map(Film::getName).distinct().collect(Collectors.toList());
        model.addAttribute("filmNames", filmNamesList);
        return "autoOrder";
    }

    @RequestMapping(value = "relevant", method = RequestMethod.GET)
    @ResponseBody
    public List<Session> getRelevantOrders(
            @RequestParam(value = "films[]", required = false) String[] films,
            @RequestParam(value = "dates[]",required = false) @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate[] dates,
            @RequestParam(value = "leftTimes[]",required = false) @DateTimeFormat(pattern = "HH:mm") LocalTime[] leftTimes,
            @RequestParam(value = "rightTimes[]",required = false) @DateTimeFormat(pattern = "HH:mm") LocalTime[] rightTimes,
            @RequestParam(value = "prices[]",required = false) int[] prices
    ) {
        return sessionService.findRelevant(films,dates,leftTimes,rightTimes,prices);

    }
}
