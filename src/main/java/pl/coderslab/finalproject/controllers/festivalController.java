package pl.coderslab.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/festival")
public class festivalController {

    @GetMapping("")
    public String displayAllFestivals(){
        return "festival/main";
    }

    @GetMapping("/add")
    public String addFestival(){
        return "festival/add";
    }

    @GetMapping("/details")
    public String festivalDetails(){
        return "festival/details";
    }

    @GetMapping("/edit")
    public String editFestivalDetails(){
        return "festival/edit";
    }

    @GetMapping("/delete")
    public String deleteFestival(){
        return "festival/delete";
    }

}
