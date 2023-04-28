package pl.coderslab.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class NoEntityController {
    @GetMapping("{festivalId}/noEvents")
    public String noEvents(@PathVariable Long festivalId, Model model) {
        model.addAttribute("festivalId", festivalId);
        return "noEntity/noEvents";
    }

    @GetMapping("{festivalId}/noPass")
    public String noPass(@PathVariable Long festivalId, Model model) {
        model.addAttribute("festivalId", festivalId);
        return "noEntity/noPass";
    }
}
