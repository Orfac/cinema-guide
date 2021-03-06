package ru.kinoguide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kinoguide.repository.OrderRepository;
import ru.kinoguide.entity.User;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    public String index(ModelMap model, @ModelAttribute("user") User user) {
        model.put("orders", orderRepository.findAllByUser(user, new PageRequest(1, 20, Sort.Direction.DESC, "dateCreated")));
        return "userOrders";
    }

}
