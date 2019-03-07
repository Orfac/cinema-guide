package ru.kinoguide.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kinoguide.entity.CinemaHall;
import ru.kinoguide.entity.CinemaNetwork;
import ru.kinoguide.service.CinemaNetworkService;

@RestController("/api/cinema-hall")
public class CinemaHallRestController {

    private CinemaNetworkService cinemaNetworkService;

    @Autowired
    public CinemaHallRestController(CinemaNetworkService cinemaNetworkService) {
        this.cinemaNetworkService = cinemaNetworkService;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<CinemaHall> getCinemaHall(@RequestAttribute("cinemaNetwork") CinemaNetwork cinemaNetwork,
                                                    @RequestParam("id") Integer cinemaHallId) {
        CinemaHall cinemaHall = cinemaNetworkService.getCinemaHall(cinemaNetwork, cinemaHallId);
        return new ResponseEntity<CinemaHall>(cinemaHall, HttpStatus.OK);
    }
}
