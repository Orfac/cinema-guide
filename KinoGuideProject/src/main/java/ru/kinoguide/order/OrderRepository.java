package ru.kinoguide.order;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface OrderRepository extends CrudRepository<Order, Integer> {
    List<Order> findAll();
}
