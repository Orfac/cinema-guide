package ru.kinoguide.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.kinoguide.Entity.Order;
import ru.kinoguide.Entity.User;

@Transactional(readOnly = true)
public interface OrderRepository extends PagingAndSortingRepository<Order, Integer> {

    Page<Order> findAllByUser(User user, Pageable pageRequest);

}
