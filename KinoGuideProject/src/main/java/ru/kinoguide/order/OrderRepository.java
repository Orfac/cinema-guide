package ru.kinoguide.order;

import org.aspectj.weaver.ast.Or;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.kinoguide.user.User;

import java.util.List;

@Transactional(readOnly = true)
public interface OrderRepository extends CrudRepository<Order, Integer> {
    List<Order> findAll();

    List<Order> findAllByUserOrderByDateDesc(User user);

}
