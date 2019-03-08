package ru.kinoguide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kinoguide.repository.SessionRepository;

@Controller
@RequestMapping("/session")
public class SessionController {

    @Autowired
    private SessionRepository sessionRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String index(ModelMap model, @PathVariable("id") int id){
        return "session";
    }

}
