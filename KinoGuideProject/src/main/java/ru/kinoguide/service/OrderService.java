package ru.kinoguide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kinoguide.entity.Order;
import ru.kinoguide.entity.User;
import ru.kinoguide.repository.OrderRepository;

@Service
@Transactional
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Page<Order> findAllByUser(User user, Pageable pageRequest) {
        return orderRepository.findAllByUser(user, pageRequest);
    }

    public Page<Order> findAllByUserId(Integer userId, Pageable pageRequest) {
        return orderRepository.findAllByUserId(userId, pageRequest);
    }
}
