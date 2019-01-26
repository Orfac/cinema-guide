package ru.kinoguide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kinoguide.entity.User;
import ru.kinoguide.repository.UserRepository;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository usersRepo;

    @Qualifier("passwordEncoder")
    @Autowired
    private PasswordEncoder passwordEncoder;

    @ModelAttribute("users")
    public Collection<User> getUsers() {
        return usersRepo.findAll();
    }

    @RequestMapping("")
    public String index(ModelMap model) {
        return "userList";
    }

    @RequestMapping({"login", "logout"})
    public String login(
            @RequestParam(name = "error", required = false) String error,
            @RequestParam(name = "logout", required = false) String logout,
            Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(@Valid User user, BindingResult userBindingResult) {
        if (!userBindingResult.hasErrors()) {
            if (usersRepo.findByName(user.getName()) == null) {
                System.out.println("//" + user.getPassword() + " //");
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                usersRepo.save(user);
                return "redirect:login";
            } else {
                userBindingResult.rejectValue("name", "Имя уже занято", "Имя уже занято");
            }
        }
        return "register";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String updateUser(User user) {
        try {
            usersRepo.save(user);
        } catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "redirect:/user";
    }


    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String deleteUser(Integer id, ModelMap model) {
        usersRepo.delete(id);
        model.put("message", "User has been deleted successfully!");
        return "redirect:/user";
    }

}
