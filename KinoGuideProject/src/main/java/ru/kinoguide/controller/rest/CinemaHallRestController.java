package ru.kinoguide.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.kinoguide.service.CinemaNetworkService;

@RestController("/api/cinema-hall")
public class CinemaHallRestController {

    private CinemaNetworkService cinemaNetworkService;

    @Autowired
    public CinemaHallRestController(CinemaNetworkService cinemaNetworkService) {
        this.cinemaNetworkService = cinemaNetworkService;
    }
}
