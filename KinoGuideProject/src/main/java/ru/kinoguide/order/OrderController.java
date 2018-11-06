package ru.kinoguide.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping("/orders")
    public String index(ModelMap model) {
        model.put("orders", orderRepository.findAll());
        return "userOrders";
    }

}
