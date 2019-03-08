package ru.kinoguide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.kinoguide.entity.User;
import ru.kinoguide.service.UserService;

import java.beans.PropertyEditorSupport;
import java.security.Principal;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class GlobalController {

    private UserService userService;

    @Autowired
    public GlobalController(UserService userService) {
        this.userService = userService;
    }


    @ModelAttribute
    public void initUser(ModelMap modelMap, Principal principal) {
        if (principal != null) {
            User user = userService.getUserByName(principal.getName());
            modelMap.put("loggedUser", user);
        }
    }

    // Moscow GMT+3 is set by default (TODO: zone offset should be user-dependent)
    @ModelAttribute("zoneOffset")
    public ZoneOffset getZoneOffset() {
        return ZoneOffset.ofHours(3);
    }
}