package ru.kinoguide.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.kinoguide.entity.Order;
import ru.kinoguide.entity.User;

@Transactional(readOnly = true)
public interface OrderRepository extends PagingAndSortingRepository<Order, Integer> {

    Page<Order> findAllByUser(User user, Pageable pageRequest);
    Page<Order> findAllByUserId(Integer userId, Pageable pageRequest);


}
