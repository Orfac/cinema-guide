package ru.kinoguide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kinoguide.entity.Film;
import ru.kinoguide.entity.User;
import ru.kinoguide.repository.FilmRepository;
import ru.kinoguide.repository.OrderRepository;
import ru.kinoguide.repository.UserRepository;
import ru.kinoguide.service.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
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
    public String getAuto(
            ModelMap model
    ){
        List<Film> filmList = filmRepository.findAll();
        List<String> filmNamesList = filmList.stream().map(Film::getName).distinct().collect(Collectors.toList());
        model.addAttribute("filmNames", filmNamesList);
        return "autoOrder";
    }

    @RequestMapping(value = "relevant", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getRelevantOrders(
            @RequestParam String[] dates,
            @RequestParam String[] leftTimes,
            @RequestParam String[] rightTimes
    ) {
        ArrayList<String> orders = new ArrayList<>();
        orders.add("privet");
        orders.add("poka");
        return orders;
    }
}
