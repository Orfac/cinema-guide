package ru.kinoguide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kinoguide.entity.User;
import ru.kinoguide.repository.OrderRepository;
import ru.kinoguide.repository.UserRepository;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("")
    public String getOrdersByUserId(
            ModelMap model,
            @RequestParam(name = "user") Integer userId,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "20") Integer ordersOnPage
    ) {
        User user = userRepository.findOne(userId);
        if (user == null) {
            model.addAttribute("error", "Пользователь не найден");
            return "error";
        }
        model.put("orders", orderRepository.findAllByUser(user, new PageRequest(page, ordersOnPage, Sort.Direction.DESC, "dateCreated")));
        return "userOrders";
    }

    @RequestMapping("auto")
    public String getAuto(
    ){
        return "autoOrder";
    }



}
