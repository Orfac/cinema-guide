package ru.kinoguide.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.kinoguide.entity.CinemaNetwork;
import ru.kinoguide.entity.Session;
import ru.kinoguide.service.SessionService;

import javax.validation.Valid;

@RestController("/api/session")
public class SessionRestController {

    private SessionService sessionService;

    @Autowired
    public SessionRestController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public ResponseEntity addSession(@RequestAttribute CinemaNetwork cinemaNetwork, @Valid Session session) {
        if (!session.getCinemaHall().getCinemaTheatre().getCinemaNetwork().equals(cinemaNetwork)) {
            return new ResponseEntity<String>("The token is not set according to the session's cinema network",
                    HttpStatus.FORBIDDEN);
        }
        Session savedSession = sessionService.addSession(session);
        return new ResponseEntity<Session>(savedSession, HttpStatus.OK);
    }

}
