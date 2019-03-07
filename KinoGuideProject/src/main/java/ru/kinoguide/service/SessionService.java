package ru.kinoguide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kinoguide.entity.Film;
import ru.kinoguide.entity.Session;
import ru.kinoguide.repository.SessionRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SessionService {
    private SessionRepository sessionRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public List<Session> findRelevant(String[] films, LocalDate[] dates,
                             LocalTime[] leftTimes, LocalTime[] rightTimes,
                             int[] Prices){
        
        // TODO время в формате ISO с учётом часового пояса пользователя
    return null;
    }
}
