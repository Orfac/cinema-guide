package ru.kinoguide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.kinoguide.entity.User;
import ru.kinoguide.repository.UserRepository;

import java.security.Principal;

@ControllerAdvice
public class GlobalController {
    @Autowired
    UserRepository userRepository;

    @ModelAttribute
    public void initUserAndFocus(ModelMap modelMap, Principal principal) {
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        if (principal != null) {
            User user = userRepository.findByName(principal.getName());
            modelMap.put("loggedUser", user);
        }
    }
}