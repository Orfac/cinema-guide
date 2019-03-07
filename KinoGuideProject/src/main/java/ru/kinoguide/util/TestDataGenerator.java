package ru.kinoguide.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kinoguide.entity.*;
import ru.kinoguide.repository.CinemaHallRepository;
import ru.kinoguide.repository.GenreRepository;
import ru.kinoguide.repository.RatingRepository;
import ru.kinoguide.repository.UserRoleRepository;
import ru.kinoguide.security.TokenGenerator;
import ru.kinoguide.service.CinemaNetworkService;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@Transactional
@Order(10)
public class TestDataGenerator implements ApplicationListener<ContextRefreshedEvent> {


    @Autowired
    @Qualifier("filmNameGenerator")
    private StringDataGenerator filmNameGenerator;

    @Autowired
    @Qualifier("filmRatingGenerator")
    private StringDataGenerator filmRatingGenerator;

    @Autowired
    @Qualifier("addressGenerator")
    private StringDataGenerator addressGenerator;

    @Autowired
    @Qualifier("cityNameGenerator")
    private StringDataGenerator cityNameGenerator;

    @Autowired
    @Qualifier("cinemaNetworkNameGenerator")
    private StringDataGenerator cinemaNetworkNameGenerator;

    @Autowired
    @Qualifier("filmPreviewGenerator")
    private StringDataGenerator filmPreviewGenerator;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private CinemaHallRepository cinemaHallRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private TokenGenerator tokenGenerator;

    @Autowired
    private CinemaNetworkService cinemaNetworkService;

    @Autowired
    private GenreRepository genreRepository;

    private final static int FILMS_NUMBER = 100;

    private final static int SESSIONS_PER_FILM = 100;

    private final static int USERS_NUMBER = 10;
    private final static int FILM_USERS_RATED = 10; // < users_number

    private final static int CINEMA_NETWORKS = 10;
    private final static int CINEMA_THEATRES_PER_NETWORK = 10;
    private final static int CINEMA_HALLS_PER_THEATER = 10;


    private final static List<Genre> genreList;

    static {
        Genre[] genres = new Genre[]{
                new Genre("драма"),
                new Genre("комедия"),
                new Genre("трагедия"),
                new Genre("мелодрама"),
                new Genre("триллер"),
                new Genre("ужасы"),
                new Genre("для детей")
        };
        genreList = Arrays.asList(genres);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        UserRole regularUserRole = new UserRole("ROLE_USER");
        UserRole adminUserRole = new UserRole("ROLE_ADMIN");

        userRoleRepository.save(regularUserRole);
        userRoleRepository.save(adminUserRole);

        ArrayList<User> userList = new ArrayList<>();
        for (int i = 0; i < USERS_NUMBER; i++) {
            User user = new User();
            user.setName("test" + i);
            user.setEmail("test@mail.ru");
            user.setPassword("$2a$10$/kuzRcxvSmGsUQMSaXxiieDYtFes8lmwhPtu4VJ8v7qw2mMO9dBAK");
            user.setPasswordRepeat("test");
            user.setUserRoles(Arrays.asList(regularUserRole));
            userList.add(user);
            entityManager.persist(user);
        }

        for (int i = 0; i < CINEMA_NETWORKS; i++) {
            CinemaNetwork cinemaNetwork = new CinemaNetwork();
            cinemaNetwork.setCinemaTheatreSet(new HashSet<>());
            cinemaNetwork.setToken(tokenGenerator.next());
            cinemaNetwork.setName(cinemaNetworkNameGenerator.generateNextValue() + " " + i);
            for (int l = 0; l < CINEMA_THEATRES_PER_NETWORK; l++) {
                CinemaTheatre cinemaTheatre = new CinemaTheatre();
                cinemaTheatre.setAddress(addressGenerator.generateNextValue());
                cinemaTheatre.setCity(cityNameGenerator.generateNextValue());
                cinemaTheatre.setCinemaNetwork(cinemaNetwork);
                cinemaNetwork.getCinemaTheatreSet().add(cinemaTheatre);
                cinemaTheatre.setCinemaHalls(new HashSet<>());
                for (int k = 0; k < CINEMA_HALLS_PER_THEATER; k++) {
                    CinemaHall cinemaHall = new CinemaHall();
                    cinemaHall.setNumber(k + 1);
                    cinemaHall.setCinemaTheatre(cinemaTheatre);
                    cinemaTheatre.getCinemaHalls().add(cinemaHall);
                }
            }
            entityManager.persist(cinemaNetwork);
        }
        List<CinemaHall> cinemaHallList = cinemaHallRepository.findAll();

        for (Genre genre : genreList) {
            entityManager.persist(genre);
        }

        for (int i = 0; i < FILMS_NUMBER; i++) {
            Film film = new Film();
            film.setName(filmNameGenerator.generateNextValue());
            film.setAgeRating(filmRatingGenerator.generateNextValue());
            film.setDuration((int) (Math.random() * 120.0 + 10));
            film.setCountry("Russia");
            film.setInfo("Info");
            film.setGenreList(new LinkedList<>());

            double genreProbability = 0.6;
            for (int k = 0; k < genreList.size(); k++) {
                if (Math.random() < genreProbability) {
                    film.addGenre(genreList.get(k));
                    genreProbability /= 2;
                    Collections.shuffle(genreList);
                }
            }
            if (film.getGenreList().size() == 0) {
                film.addGenre(genreList.get(0));
            }

            film.setAnnotation("Annotation");
            film.setDatePremiere(OffsetDateTime.of((int) (Math.random() * 5 + 2018), (int) (Math.random() * 11) + 1, (int) (Math.random() * 27) + 1, 0, 0, 0, 0, ZoneOffset.UTC).toInstant());

            if (Math.random() > 0.1) {
                // Film preview image
                Media media = new Media();
                media.setType(Film.PREVIEW_IMAGE_MEDIA_TYPE);
                media.setUrl(filmPreviewGenerator.generateNextValue());

                EntityMedia entityMedia = new EntityMedia();
                entityMedia.setDisplayableEntity(film);
                entityMedia.setMediaList(new LinkedList<>(Arrays.asList(new Media[]{media})));
                media.setEntity(entityMedia);
                film.setMediaEntity(entityMedia);
            }

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

            for (int k = 0; k < SESSIONS_PER_FILM; k++) {
                Session session = new Session();
                Instant startInstant = film.getDatePremiere().plus((int) (Math.random() * 30) + 60, ChronoUnit.DAYS);
                session.setFilm(film);
                session.setStartTime(startInstant);
                session.setEndTime(startInstant.plus(film.getDuration(), ChronoUnit.MINUTES));
                session.setCinemaHall(cinemaHallList.get((int) (Math.random() * cinemaHallList.size())));
                entityManager.persist(session);
            }
        }
        entityManager.flush();
    }
}
