package ru.kinoguide.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.kinoguide.user.User;

@Transactional(readOnly = true)
public interface OrderRepository extends PagingAndSortingRepository<Order, Integer> {

    Page<Order> findAllByUser(User user, Pageable pageRequest);

}
