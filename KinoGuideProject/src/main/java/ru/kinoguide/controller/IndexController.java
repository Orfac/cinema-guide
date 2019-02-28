package ru.kinoguide.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController implements ErrorController {

    private final String PATH = "error";

    @Override
    public String getErrorPath() {
        return PATH;
    }

    @RequestMapping("/error")
    public String handleError(ModelMap modelMap) {
        modelMap.put("error", "Упс!"); // TODO output exception?
        return "error";
    }

    @RequestMapping("/access-denied")
    public String accessDenied(Model model) {
        model.addAttribute("error", "Доступ запрещен");
        return "access-denied";
    }

    @RequestMapping(value = {"", "/"})
    public String root() {
        return "redirect:/film/billboard";
    }
}
