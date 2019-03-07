package ru.kinoguide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kinoguide.entity.CinemaNetwork;
import ru.kinoguide.entity.Seat;
import ru.kinoguide.entity.Session;
import ru.kinoguide.entity.Ticket;
import ru.kinoguide.repository.SessionRepository;

@Service
@Transactional
public class SessionService {

    private SessionRepository sessionRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public Session addSession(Session session) {
        return sessionRepository.save(session);
    }
}
