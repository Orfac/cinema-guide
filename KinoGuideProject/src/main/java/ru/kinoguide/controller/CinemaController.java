package ru.kinoguide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kinoguide.entity.CinemaNetwork;
import ru.kinoguide.repository.CinemaNetworkRepository;
import ru.kinoguide.repository.CinemaTheatreRepository;

@Controller
@RequestMapping("/cinema")
public class CinemaController {

    @Autowired
    private CinemaTheatreRepository cinemaTheatreRepository;

    @Autowired
    private CinemaNetworkRepository cinemaNetworkRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    String getId(ModelMap model, @RequestParam String cinemaId){
        int intId = Integer.parseInt(cinemaId);
        model.put("cinemaTheatre", cinemaTheatreRepository.findById(intId));
        return "cinemaTheatre";
    }
}
