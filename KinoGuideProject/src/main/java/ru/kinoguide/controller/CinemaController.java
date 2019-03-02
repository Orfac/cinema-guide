package ru.kinoguide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kinoguide.entity.CinemaNetwork;
import ru.kinoguide.entity.CinemaTheatre;
import ru.kinoguide.repository.CinemaNetworkRepository;
import ru.kinoguide.repository.CinemaTheatreRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cinema")
public class CinemaController {

    private final int HOUR_IN_DAY = 24;

    @Autowired
    private CinemaTheatreRepository cinemaTheatreRepository;

    @Autowired
    private CinemaNetworkRepository cinemaNetworkRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String index(ModelMap model, @PathVariable("id") int id,
                        @RequestParam(required=false) @DateTimeFormat(pattern="MMddyyyy") Date day){
        CinemaTheatre cinemaTheatre = cinemaTheatreRepository.findById(id);
        if (cinemaTheatre == null) {
            return "error";
        }
        // Задаёт текущую дату, если дата не была задана
        if (day == null) {
            day = Calendar.getInstance().getTime();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);


        cal.add(Calendar.HOUR,-4*HOUR_IN_DAY);
        ArrayList<Date> week = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            cal.add(Calendar.HOUR,HOUR_IN_DAY);
            week.add(cal.getTime());
        }

        model.addAttribute("week", week);
        model.addAttribute("currentDay", day);

        //model.put("cinemaNetwork",cinemaTheatre.getCinemaNetwork());
        model.addAttribute("cinemaTheatre", cinemaTheatre);
        return "cinemaTheatre";
    }

    @RequestMapping(value = "listAddresses", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getAllCinemaTheatres() {
        List<CinemaTheatre> cinemaTheatresList = cinemaTheatreRepository.findAll();
        return cinemaTheatresList.stream()
                .map(CinemaTheatre::getAddress)
                .distinct()
                .collect(Collectors.toList());
    }
}
