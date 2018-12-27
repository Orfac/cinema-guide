package ru.kinoguide.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.kinoguide.user.User;

import java.time.Instant;

public interface SessionRepository extends PagingAndSortingRepository<Session, Integer> {
    @Query("select s from Session s where s.startTime > CURRENT_DATE")
    Page<Session> findAllSinceNow(Pageable pageable);

    @Query("select s from Session s where s.startTime > instant")
    Page<Session> findAllSinceDate(Pageable pageable, Instant instant);
}
