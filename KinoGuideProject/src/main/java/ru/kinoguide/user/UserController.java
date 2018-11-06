package ru.kinoguide.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository usersRepo;

    @ModelAttribute("users")
    public Collection<User> getUsers() {
        return usersRepo.findAll();
    }

    @RequestMapping("")
    public String index(ModelMap model) {
        return "userList";
    }

    @RequestMapping("/login")
    public String login(ModelMap model, @RequestParam(name = "error", required = false) String error, @RequestParam(name = "logout", required = false) String logout) {
        if (error != null) {
            model.put("error", "Invalid username or password");
        }
        if (logout != null) {
            model.put("logout", "You have logout successfully");
        }
        return "login";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String updateUser(User user) {
        try {
            usersRepo.save(user);
        } catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "redirect:/users";
    }

    @RequestMapping(value = "update", method = RequestMethod.GET)
    public String updateUserForm(Integer id, ModelMap model) {
        User user = id != null ? usersRepo.findOne(id) : null;
        if (user == null) {
            user = new User();
        }
        model.put("user", user);
        return "updateUser";
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String deleteUser(Integer id, ModelMap model) {
        usersRepo.delete(id);
        model.put("message", "User has been deleted successfully!");
        return "redirect:/users";
    }

}
