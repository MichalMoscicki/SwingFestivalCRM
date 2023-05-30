package pl.coderslab.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.finalproject.models.event.Event;
import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.models.pass.Pass;
import pl.coderslab.finalproject.repositories.EventRepository;
import pl.coderslab.finalproject.service.FestivalService;
import pl.coderslab.finalproject.service.PassService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/pass")
public class PassController {

    private final EventRepository eventRepository;
    private final FestivalService festivalService;
    private final PassService passService;

    public PassController(EventRepository eventRepository,
                          FestivalService festivalService,
                          PassService passService) {
        this.eventRepository = eventRepository;
        this.festivalService = festivalService;
        this.passService = passService;
    }

    @GetMapping("{festivalId}/add")
    public String addPass(@PathVariable Long festivalId, Model model) {
        model.addAttribute("festivalId", festivalId);
        model.addAttribute("pass", new Pass());
        Festival festival = festivalService.findFestival(festivalId);
        List<Event> eventList = eventRepository.findAllByFestivalOrderByStart(festival);
        if (eventList.isEmpty()) {
            return String.format("redirect:/%s/noEvents", festivalId);
        }
        model.addAttribute("events", eventList);
        return "pass/add";
    }

    @PostMapping("{festivalId}/add")
    public String addPass(@Valid Pass pass, BindingResult result,
                          @PathVariable Long festivalId) {
        if (result.hasErrors()) {
            return String.format("/pass/%s/add", festivalId);
        }
        Festival festival = festivalService.findFestival(festivalId);
        pass.setFestival(festival);
        passService.add(pass);
        return String.format("redirect:/festival/details/%s", festivalId);
    }

    @GetMapping("{festivalId}/confirmDelete/{passId}")
    public String displayDeleteConfirmation(@PathVariable Long festivalId,
                                            @PathVariable Long passId, Model model) {
        model.addAttribute("festivalId", festivalId);
        Pass pass = passService.findById(passId);
        model.addAttribute("pass", pass);
        return "pass/confirmDelete";
    }

    @GetMapping("delete/{festivalId}/{passId}")
    public String delete(@PathVariable Long festivalId,
                                @PathVariable Long passId) {
        passService.delete(passId);
        return String.format("redirect:/festival/details/%s", festivalId);

    }

    @GetMapping("{festivalId}/details/{passId}")
    public String displayDetails(@PathVariable Long festivalId,
                                            @PathVariable Long passId, Model model) {
        model.addAttribute("festivalId", festivalId);
        Pass pass = passService.findById(passId);
        model.addAttribute("pass", pass);
        List<Event> events = pass.getEvents();
        model.addAttribute("events", events);

        return "pass/details";
    }


    @GetMapping("{festivalId}/edit/{passId}")
    public String editPass(@PathVariable Long festivalId,
                                @PathVariable Long passId, Model model){
        Pass pass = passService.findById(passId);
        model.addAttribute("festivalId", festivalId);
        model.addAttribute("pass", pass);
        List<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);
        return "pass/edit";
    }

    @PostMapping("{festivalId}/edit/{passId}")
    public String editPass(@Valid Pass pass, BindingResult result,
                           @PathVariable Long festivalId,
                           @PathVariable Long passId){
        if (result.hasErrors()) {
            return String.format("redirect:/pass/%s/edit/%s", festivalId, passId);
        }
        Festival festival = festivalService.findFestival(festivalId);
        pass.setFestival(festival);
        passService.add(pass);
        return String.format("redirect:/festival/details/%s", festivalId);
    }

}