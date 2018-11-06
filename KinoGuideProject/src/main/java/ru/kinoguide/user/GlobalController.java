package ru.kinoguide.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@ControllerAdvice
public class GlobalController {
    @Autowired
    UserRepository userRepository;

    @ModelAttribute
    public void initUserAndFocus(ModelMap modelMap, Principal principal) {
        if (principal != null) {
            User user = userRepository.findByName(principal.getName());
            modelMap.put("user", user);
        }
    }
}
