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
import ru.kinoguide.service.FilmService;
import ru.kinoguide.service.OrderService;
import ru.kinoguide.service.SessionService;
import ru.kinoguide.service.relevance.RelevanceService;
import ru.kinoguide.view.SessionViewModel;

import java.time.Instant;
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
    private FilmService filmService;
    private RelevanceService relevanceService;

    @Autowired
    public OrderController(OrderService orderService, RelevanceService relevanceService,SessionService sessionService, FilmService filmService){
        this.orderService = orderService;
        this.sessionService = sessionService;
        this.filmService = filmService;
        this.relevanceService = relevanceService;
    }



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
        List<Film> filmList = filmService.findFilmsWhichHaveSessionsSinceNow();
        List<String> filmNamesList = filmList.stream().map(Film::getName).distinct().collect(Collectors.toList());
        model.addAttribute("filmNames", filmNamesList);
        return "autoOrder";
    }

    @RequestMapping(value = "relevant", method = RequestMethod.GET)
    @ResponseBody
    public SessionViewModel[] getRelevantOrders(
            @RequestParam(value = "films[]", required = false) String[] films,
            @RequestParam(value = "leftInstant[]",required = false) @DateTimeFormat(pattern = "HH:mm") Instant[] startTimes,
            @RequestParam(value = "rightInstant[]",required = false) @DateTimeFormat(pattern = "HH:mm") Instant[] endTimes,
            @RequestParam(value = "price",required = false) double price
    ) {
        List<Session> relevantSessions = relevanceService.findRelevant(films,startTimes,endTimes,price);
        SessionViewModel[] viewModels = new SessionViewModel[relevantSessions.size()];
        for (int i = 0; i < relevantSessions.size(); i++) {
            Film film = relevantSessions.get(i).getFilm();
            viewModels[i].setFilmId(film.getId());
            viewModels[i].setFilmName(film.getName());
            viewModels[i].setImageLink("http://images.filmfestival.be/image/filmfest/900-0/16-1455_poster.jpg");
            viewModels[i].setSessionId(relevantSessions.get(i).getId());
        }
        return viewModels;
    }
}
