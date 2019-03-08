package ru.kinoguide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kinoguide.entity.Film;
import ru.kinoguide.entity.Session;
import ru.kinoguide.entity.User;
import ru.kinoguide.service.FilmService;
import ru.kinoguide.service.GenreService;
import ru.kinoguide.service.SessionService;
import ru.kinoguide.service.proximity.ProximityService;
import ru.kinoguide.util.DateTimeUtils;
import ru.kinoguide.view.CinemaTheatresSessionsView;
import ru.kinoguide.view.GenreView;

import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/film")
public class FilmsController {

    private static final int TOP_PROXIMITY_USER_RATES = 5;

    private static final int CINEMA_THEATRES_PER_PAGE = 5;

    private static final int DAYS_SHOWN = 7;

    private static final int FILMS_ON_BILLBOARD = 12;

    private FilmService filmService;

    private SessionService sessionService;

    private ProximityService proximityService;

    private GenreService genreService;

    @Autowired
    public FilmsController(FilmService filmService, SessionService sessionService, ProximityService proximityService, GenreService genreService) {
        this.filmService = filmService;
        this.sessionService = sessionService;
        this.proximityService = proximityService;
        this.genreService = genreService;
    }

    @RequestMapping(value = "billboard", method = RequestMethod.GET)
    public String billboard(
            ModelMap model,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "sortField", required = false, defaultValue = "datePremiere") String sortField,
            @RequestParam(name = "sortDirectionDesc", required = false, defaultValue = "true") boolean isSortDirectionDesc, // should be false if ASC
            @RequestParam(name = "genres", required = false) Integer[] genreIds

    ) {
        if (genreIds == null || genreIds.length == 0) {
            genreIds = genreService.findAll().stream().map(genre -> genre.getId()).toArray(Integer[]::new);
        }

        PageRequest pageRequest = new PageRequest(page, FILMS_ON_BILLBOARD, isSortDirectionDesc ? Sort.Direction.DESC : Sort.Direction.ASC, sortField);

        List<Integer> genreIdsList = Arrays.asList(genreIds);
        Page<Film> filmPage = filmService.findBySessionStartTimeAfterAndGenreIdIn(pageRequest, Instant.now(), genreIds);
        model.put("films", filmPage);
        model.put("genres", genreService.findAll().stream().
                map(genre ->
                        new GenreView(genreIdsList.indexOf(genre.getId()) != -1, genre))
                .collect(Collectors.toList()));
        return "billboard";
    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String film(
            ModelMap modelMap,
            @RequestParam(name = "id") Integer id,
            @RequestParam(name = "date", required = false) LocalDate sessionsDate,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @ModelAttribute(name = "zoneOffset", binding = false) ZoneOffset zoneOffset
    ) {
        Film film = filmService.findOne(id);
        if (film == null) {
            modelMap.addAttribute("error", "Фильм не найден");
            return "error";
        }

        if (sessionsDate == null) {
            sessionsDate = OffsetDateTime.now().withOffsetSameInstant(zoneOffset).toLocalDate();
        }

        Instant sessionRangeStart = sessionsDate.atStartOfDay().toInstant(zoneOffset);
        Instant sessionRangeEnd = sessionsDate.atTime(23, 59, 59).toInstant(zoneOffset);

        Iterable<Session> sessionPage = sessionService.findByFilmAndStartTimeBetweenOrderByStartTimeAsc(film, sessionRangeStart, sessionRangeEnd);

        List<Session> sessionList = new ArrayList<>();
        for (Session session : sessionPage) {
            sessionList.add(session);
        }

        LocalDate currDate;
        if (sessionRangeStart.isAfter(Instant.now())) { // so previous day(-s) have not passed
            int daysBetweenCeiled = DateTimeUtils.getDaysBetweenInstantsCeiled(Instant.now(), sessionRangeStart);
            currDate = sessionsDate.minusDays(DAYS_SHOWN / 2 > daysBetweenCeiled ? daysBetweenCeiled : DAYS_SHOWN / 2);
        } else {
            currDate = sessionsDate;
        }
        List<LocalDate> datesList = new ArrayList<>();
        for (int i = 0; i < DAYS_SHOWN; i++) {
            datesList.add(currDate);
            currDate = currDate.plusDays(1);
        }

        User loggedUser = (User) modelMap.get("loggedUser");
        if (loggedUser != null) {
            modelMap.addAttribute("closeUserRelatedProximityList", proximityService.getUsersWithClosestProximityByFilm(loggedUser, film, TOP_PROXIMITY_USER_RATES));
        }
        modelMap.addAttribute("film", film);
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("dates", datesList);
        modelMap.addAttribute("cinemaTheatresSessions", new CinemaTheatresSessionsView(sessionList, page, CINEMA_THEATRES_PER_PAGE));
        modelMap.addAttribute("currentDate", sessionsDate);

        return "film";
    }
}
