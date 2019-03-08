package ru.kinoguide.service.relevance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kinoguide.entity.Session;
import ru.kinoguide.entity.Ticket;
import ru.kinoguide.repository.SessionRepository;
import ru.kinoguide.repository.TicketsRepository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RelevanceService {
    private SessionRepository sessionRepository;
    private TicketsRepository ticketsRepository;

    @Autowired
    public RelevanceService(SessionRepository sessionRepository, TicketsRepository ticketsRepository) {
        this.sessionRepository = sessionRepository;
        this.ticketsRepository = ticketsRepository;
    }

    public List<Session> findRelevant(String[] films, Instant[] startTimes, Instant[] endTimes, Double price) {
        // TODO время в формате ISO с учётом часового пояса пользователя
        List<Session> sessions;
        if (films == null) {
            if (price == null) {
                sessions = selectSessionsByTime(sessionRepository.findAllSinceNow(), startTimes,endTimes);
            } else {
                List<Ticket> tickets = ticketsRepository.findTicketsByPriceLessThanEqual(price);
                sessions = selectSessionsByTime(selectSessionsByTickets(tickets), startTimes,endTimes);
            }
        } else {
            sessions = sessionRepository.findSessionsByFilms(films);
            if (price != null){
                sessions = selectSessionsByPrice(sessions,price);
            }
        }
        return selectSessionsByTime(sessions,startTimes,endTimes);
    }

    private List<Session> selectSessionsByPrice(List<Session> sessions, Double price) {
        List<Session> resultSessions = new ArrayList<>();
        for (Session session : sessions){
            if (isSessionFitsByPrice(session,price)){
                resultSessions.add(session);
            }
        }
        return resultSessions;
    }

    private List<Session> selectSessionsByTickets(List<Ticket> tickets) {
        Set<Session> sessions = new HashSet<>();
        for (int i = 0; i < tickets.size(); i++) {
            sessions.add(tickets.get(i).getSession());
        }
        return new ArrayList<>(sessions);
    }

    private List<Session> selectSessionsByTime(List<Session> sessions, Instant[] startTimes, Instant[] endTimes ){
        if (startTimes == null || endTimes == null) {
            return sessions;
        }
        List<Session> resultSessions = new ArrayList<>();
        for (int i = 0; i < sessions.size(); i++) {
            if (isSessionFitsByTime(sessions.get(i),startTimes,endTimes)){
                resultSessions.add(sessions.get(i));
            }
        }
        return resultSessions;
    }

    private boolean isSessionFitsByTime(Session session, Instant[] startTimes, Instant[] endTimes){
        for (int i = 0; i < startTimes.length; i++)
            if (session.getStartTime().compareTo(startTimes[i]) <= 0 &&
                    session.getEndTime().compareTo(endTimes[i]) >= 0){
                return true;
            }
        return false;
    }

    private boolean isSessionFitsByPrice(Session session, double price){
        Set<Ticket> tickets = session.getTickets();
        for (Ticket ticket : tickets){
            if (ticket.getPrice() <= price){
                return true;
            }
        }
        return false;
    }
}
