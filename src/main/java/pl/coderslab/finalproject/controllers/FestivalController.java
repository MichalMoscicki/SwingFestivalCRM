package pl.coderslab.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.models.event.Event;
import pl.coderslab.finalproject.models.pass.Pass;
import pl.coderslab.finalproject.repositories.EventRepository;
import pl.coderslab.finalproject.service.EventService;
import pl.coderslab.finalproject.service.FestivalService;
import pl.coderslab.finalproject.service.PassService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/festival")
public class FestivalController {

    private final FestivalService festivalService;
    private final EventService eventService;
    private final PassService passService;


    public FestivalController(FestivalService festivalService,
                              EventService eventService,
                              PassService passService) {
        this.festivalService = festivalService;
        this.eventService = eventService;
        this.passService = passService;
    }

    @GetMapping("/all")
    public String displayAllFestivals(Model model){
        List<Festival> festivals = festivalService.findAll();
        model.addAttribute("festivals", festivals);
        return "festivalAll";
    }

    @GetMapping("/add")
    public String addFestival(Model model) {
        model.addAttribute("festival", new Festival());
        return "festival/add";
    }

    @PostMapping("/add")
    public String addFestival(@Valid Festival festival, BindingResult res) {
        if (res.hasErrors()) {
            return "festival/add";
        }
        festivalService.addFestival(festival);
        return "redirect:/festival/all";
    }

    @GetMapping("/details/{id}")
    public String festivalDetails(@PathVariable Long id, Model model) {
        Festival festival = festivalService.findFestival(id);
        model.addAttribute("festival", festival);
        List<Event> events = eventService.findAllByFestivalOrderByStart(festival);
        model.addAttribute("events", events);
        List<Pass> passes = passService.findAllByFestival(festival);
        model.addAttribute("passes", passes);

        return "festival/details";
    }

    @GetMapping("/edit/{id}")
    public String editFestivalDetails(Model model, @PathVariable Long id) {
        Festival festival = festivalService.findFestival(id);
        model.addAttribute("festival", festival);
        return "festival/edit";
    }

    @PostMapping("/edit/{id}")
    public String editFestivalDetails(@Valid Festival festival, BindingResult res) {
        if (res.hasErrors()) {
            return "festival/edit";
        }
        festivalService.addFestival(festival);
        return "redirect:/festival/all";
    }

    @GetMapping("/deleteConfirm/{id}")
    public String deleteFestivalConfirmation(@PathVariable Long id, Model model) {
        Festival festival = festivalService.findFestival(id);
        model.addAttribute("festival", festival);
        return "/festival/delete";
    }

    @GetMapping("/delete/{id}")
    public String deleteFestival(@PathVariable Long id) {
        festivalService.deleteFestival(id);
        return "redirect:/festival/all";
    }
}
