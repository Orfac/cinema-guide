package ru.kinoguide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kinoguide.entity.User;
import ru.kinoguide.exception.NameOccupiedException;
import ru.kinoguide.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {

    private UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("login")
    public String login(
            @RequestParam(name = "error", required = false) String error,
            @RequestParam(name = "logout", required = false) String logout,
            Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register(User user) {
        return "register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(@Valid User user, BindingResult userBindingResult) {
        if (!userBindingResult.hasFieldErrors("password") && !userBindingResult.hasFieldErrors("passwordRepeat")) {
            if (!user.getPassword().equals(user.getPasswordRepeat())) {
                userBindingResult.rejectValue("passwordRepeat", "Пароли должны совпадать", "Пароли должны совпадать");
            }
        }
        if (!userBindingResult.hasErrors()) {
            try {
                userService.registerUser(user);
                return "redirect:login";
            } catch (NameOccupiedException ex) {
                userBindingResult.rejectValue("name", "Имя уже занято", "Имя уже занято");
            }
        }
        return "register";
    }

}
