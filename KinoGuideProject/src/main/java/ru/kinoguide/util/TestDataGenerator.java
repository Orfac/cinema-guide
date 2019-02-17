package ru.kinoguide.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kinoguide.entity.Film;
import ru.kinoguide.entity.Rating;
import ru.kinoguide.entity.User;
import ru.kinoguide.repository.RatingRepository;
import ru.kinoguide.service.LinearUserProximityCalculator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;

@Service
@Transactional
@Order(10)
public class TestDataGenerator implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private EntityManager entityManager;


    @Autowired
    @Qualifier("filmNameGenerator")
    private StringDataGenerator filmNameGenerator;

    @Autowired
    @Qualifier("filmRatingGenerator")
    private StringDataGenerator filmRatingGenerator;

    @Autowired
    private RatingRepository ratingRepository;

    private final static int FILMS_NUMBER = 100;

    private final static int USERS_NUMBER = 10;
    private final static int FILM_USERS_RATED = 10; // < users_number


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ArrayList<User> userList = new ArrayList<>();
        for (int i = 0; i < USERS_NUMBER; i++) {
            User user = new User();
            user.setName("test" + i);
            user.setEmail("test@mail.ru");
            user.setPassword("test");
            user.setPasswordRepeat("test");
            userList.add(user);
            entityManager.persist(user);
        }

        for (int i = 0; i < FILMS_NUMBER; i++) {
            Film film = new Film();
            film.setName(filmNameGenerator.generateNextValue());
            film.setAgeRating(filmRatingGenerator.generateNextValue());
            film.setDuration((int) Math.random() * 100 + 10);
            film.setCountry("Russia");
            film.setInfo("Info");
            film.setAnnotation("Annotation");
            film.setDateShootingStart(Instant.now().minusSeconds(60 * 60 * 10));
            entityManager.persist(film);
            Collections.shuffle(userList);
            for (int k = 0; k < FILM_USERS_RATED; k++) {
                Rating rating = new Rating();
                rating.setFilm(film);
                rating.setRate((int) (Math.random() * 10 + 1));
                rating.setUser(userList.get(k));
                rating.setDate(Instant.now().minusSeconds((long) (1000 * Math.random() * 1000)));
                entityManager.persist(rating);
            }
        }
        entityManager.flush();
    }
}
