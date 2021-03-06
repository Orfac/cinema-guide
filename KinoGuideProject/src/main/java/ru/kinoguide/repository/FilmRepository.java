package ru.kinoguide.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.kinoguide.entity.Film;
import ru.kinoguide.entity.Session;

@Transactional(readOnly = true)
public interface FilmRepository extends PagingAndSortingRepository<Film, Integer> {
    @Query("SELECT f from Film f WHERE EXISTS " +
            "(SELECT s FROM Session s WHERE s.startTime > CURRENT_DATE and s.film = f)")
    Page<Film> findFilmsWhichHaveSessionsSinceNow(Pageable pageable);
}
