package ru.kinoguide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kinoguide.entity.CinemaHall;
import ru.kinoguide.entity.CinemaNetwork;
import ru.kinoguide.entity.CinemaTheatre;
import ru.kinoguide.repository.CinemaHallRepository;
import ru.kinoguide.repository.CinemaNetworkRepository;
import ru.kinoguide.repository.CinemaTheatreRepository;
import ru.kinoguide.security.TokenGenerator;

import java.util.List;

@Service
@Transactional
public class CinemaNetworkService {

    private TokenGenerator secureTokenGenerator;

    private CinemaNetworkRepository cinemaNetworkRepository;

    private CinemaTheatreRepository cinemaTheatreRepository;

    private CinemaHallRepository cinemaHallRepository;

    @Autowired
    public CinemaNetworkService(CinemaNetworkRepository cinemaNetworkRepository, CinemaHallRepository cinemaHallRepository, CinemaTheatreRepository cinemaTheatreRepository, TokenGenerator tokenGenerator) {
        this.cinemaNetworkRepository = cinemaNetworkRepository;
        this.secureTokenGenerator = tokenGenerator;
        this.cinemaTheatreRepository = cinemaTheatreRepository;
    }

    public CinemaNetwork addCinemaNetwork(CinemaNetwork cinemaNetwork) {
        if (!cinemaNetwork.isNew()) {
            throw new IllegalArgumentException(("Cinema network is already persisted"));
        }
        return cinemaNetworkRepository.save(cinemaNetwork);
    }

    public CinemaHall addCinemaHall(CinemaHall cinemaHall) {
        if (!cinemaHall.isNew()) {
            throw new IllegalArgumentException(("Cinema hall is already persisted"));
        }
        return cinemaHallRepository.save(cinemaHall);
    }

    public CinemaTheatre findById(int id) {
        return cinemaTheatreRepository.findById(id);
    }

    public CinemaHall getCinemaHall(CinemaNetwork cinemaNetwork, int number) {
        return cinemaHallRepository.findOneByNumberAndCinemaTheatreCinemaNetwork(cinemaNetwork, number);
    }

    public CinemaNetwork findCinemaNetworkByToken(String token) {
        return cinemaNetworkRepository.findOneByToken(token);
    }

    private String generateAuthenticationToken() {
        return secureTokenGenerator.next();
    }

    public List<CinemaTheatre> findAllCinemaTheatres() {
        return cinemaTheatreRepository.findAll();
    }
}
