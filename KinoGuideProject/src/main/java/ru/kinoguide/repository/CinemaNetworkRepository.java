package ru.kinoguide.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.kinoguide.entity.CinemaNetwork;

public interface CinemaNetworkRepository extends PagingAndSortingRepository<CinemaNetwork,Integer> {

    CinemaNetwork findOneByToken(String token);
}
