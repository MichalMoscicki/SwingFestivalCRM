package pl.coderslab.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/event")
public class eventController {

    @GetMapping("/details")
    public String displayEventDetails() {
        return "event/details";
    }

    @GetMapping("/edit")
    public String editEventDetails() {
        return "event/edit";
    }

    @GetMapping("/add")
    public String addEvent() {
        return "event/add";
    }

    @GetMapping("/delete")
    public String deleteEvent() {
        return "event/delete";
    }


}
