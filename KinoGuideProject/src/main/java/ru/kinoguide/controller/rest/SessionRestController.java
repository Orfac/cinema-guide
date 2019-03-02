package ru.kinoguide.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.kinoguide.service.SessionService;

@RestController("/api/session")
public class SessionRestController {

    private SessionService sessionService;

    @Autowired
    public SessionRestController(SessionService sessionService) {
        this.sessionService = sessionService;
    }
}
