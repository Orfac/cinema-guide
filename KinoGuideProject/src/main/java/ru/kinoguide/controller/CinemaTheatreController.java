package ru.kinoguide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kinoguide.entity.CinemaTheatre;
import ru.kinoguide.entity.Session;
import ru.kinoguide.entity.User;
import ru.kinoguide.service.CinemaNetworkService;
import ru.kinoguide.service.SessionService;
import ru.kinoguide.util.DateTimeUtils;
import ru.kinoguide.view.CinemaTheatresSessionsView;
import ru.kinoguide.view.FilmsSessionsView;

import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cinema")
public class CinemaTheatreController {


    private static final int FILMS_PER_PAGE = 5;

    private static final int DAYS_SHOWN = 7;

    private SessionService sessionService;

    private CinemaNetworkService cinemaNetworkService;

    @Autowired
    public CinemaTheatreController(SessionService sessionService, CinemaNetworkService cinemaNetworkService) {
        this.sessionService = sessionService;
        this.cinemaNetworkService = cinemaNetworkService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getCinemaTheatre(
            ModelMap modelMap,
            @RequestParam(name = "id") Integer id,
            @RequestParam(name = "date", required = false) LocalDate sessionsDate,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @ModelAttribute(name = "zoneOffset", binding = false) ZoneOffset zoneOffset
    ) {
        CinemaTheatre cinemaTheatre = cinemaNetworkService.findById(id);
        if (cinemaTheatre == null) {
            modelMap.addAttribute("error", "Кинотеатр не найден");
            return "error";
        }

        if (sessionsDate == null) {
            sessionsDate = OffsetDateTime.now().withOffsetSameInstant(zoneOffset).toLocalDate();
        }

        Instant sessionRangeStart = sessionsDate.atStartOfDay().toInstant(zoneOffset);
        Instant sessionRangeEnd = sessionsDate.atTime(23, 59, 59).toInstant(zoneOffset);

        Iterable<Session> sessionPage = sessionService.findByCinemaHallCinemaTheatreAndStartTimeBetweenOrderByStartTimeAsc(cinemaTheatre, sessionRangeStart, sessionRangeEnd);

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

        modelMap.addAttribute("cinemaTheatre", cinemaTheatre);
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("dates", datesList);
        modelMap.addAttribute("filmsSessions", new FilmsSessionsView(sessionList, page, FILMS_PER_PAGE));
        modelMap.addAttribute("currentDate", sessionsDate);

        return "cinemaTheatre";
    }

    @RequestMapping(value = "listAddresses", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getAllCinemaTheatres() {
        List<CinemaTheatre> cinemaTheatresList = cinemaNetworkService.findAllCinemaTheatres();
        return cinemaTheatresList.stream()
                .map(CinemaTheatre::getAddress)
                .distinct()
                .collect(Collectors.toList());
    }
}
