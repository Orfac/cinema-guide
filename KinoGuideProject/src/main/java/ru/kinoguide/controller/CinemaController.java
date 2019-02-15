package ru.kinoguide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kinoguide.entity.CinemaNetwork;
import ru.kinoguide.entity.CinemaTheatre;
import ru.kinoguide.repository.CinemaNetworkRepository;
import ru.kinoguide.repository.CinemaTheatreRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping("/cinema")
public class CinemaController {

    @Autowired
    private CinemaTheatreRepository cinemaTheatreRepository;

    @Autowired
    private CinemaNetworkRepository cinemaNetworkRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String index(ModelMap model, @PathVariable("id") int id,
                        @RequestParam(required=false) @DateTimeFormat(pattern="MMddyyyy") Date day){
        CinemaTheatre cinemaTheatre = cinemaTheatreRepository.findById(id);
        //if (cinemaTheatre == null){return "error";}
        if (day == null) {
            Calendar today = Calendar.getInstance();
            today.set(Calendar.HOUR_OF_DAY, 0);
            day = today.getTime();
        }
        Date currentDate = day;
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        cal.add(Calendar.HOUR,-4*24);
        ArrayList<Date> dates = new ArrayList<>(7);
        for (int i = 0; i < 7; i++) {
            cal.add(Calendar.HOUR,24);
            cal.setTime(dates.get(i));
        }

        model.addAttribute("week", dates);
        model.addAttribute("currentDate", currentDate);

        //model.put("cinemaNetwork",cinemaTheatre.getCinemaNetwork());
        //model.put("cinemaTheatre", cinemaTheatre);
        return "cinemaTheatre";
    }
}
