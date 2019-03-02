package ru.kinoguide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.kinoguide.entity.User;
import ru.kinoguide.service.UserService;

import java.security.Principal;
import java.time.ZoneOffset;

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

    // Moscow GMT+3 (TODO: zone offset should be user-dependent)
    @ModelAttribute("zoneOffset")
    public ZoneOffset getZoneOffset() {
        return ZoneOffset.ofHours(3);
    }
}