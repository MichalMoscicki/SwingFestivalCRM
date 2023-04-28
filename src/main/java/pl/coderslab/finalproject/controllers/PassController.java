package pl.coderslab.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.finalproject.models.event.Event;
import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.models.pass.Pass;
import pl.coderslab.finalproject.repositories.EventRepository;
import pl.coderslab.finalproject.repositories.FestivalRepository;
import pl.coderslab.finalproject.repositories.PassRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pass")
public class PassController {

    private final PassRepository passRepository;
    private final EventRepository eventRepository;
    private final FestivalRepository festivalRepository;

    public PassController(PassRepository passRepository, EventRepository eventRepository, FestivalRepository festivalRepository) {
        this.passRepository = passRepository;
        this.eventRepository = eventRepository;
        this.festivalRepository = festivalRepository;
    }

    @GetMapping("{festivalId}/add")
    public String addPass(@PathVariable Long festivalId, Model model) {
        model.addAttribute("festivalId", festivalId);
        model.addAttribute("pass", new Pass());
        Optional<Festival> festivalOptional = festivalRepository.findById(festivalId);
        List<Event> eventList = eventRepository.findAllByFestival(festivalOptional.get());
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
        Optional<Festival> festivalOptional = festivalRepository.findById(festivalId);
        pass.setFestival(festivalOptional.get());
        passRepository.save(pass);
        return String.format("redirect:/festival/details/%s", festivalId);
    }

    @GetMapping("{festivalId}/confirmDelete/{passId}")
    public String displayDeleteConfirmation(@PathVariable Long festivalId,
                                            @PathVariable Long passId, Model model) {
        model.addAttribute("festivalId", festivalId);
        Optional<Pass> passOptional = passRepository.findById(passId);
        model.addAttribute("pass", passOptional.get());
        return "pass/confirmDelete";
    }

    @GetMapping("delete/{festivalId}/{passId}")
    public String delete(@PathVariable Long festivalId,
                                @PathVariable Long passId) {
        passRepository.deleteById(passId);
        return String.format("redirect:/festival/details/%s", festivalId);

    }

    @GetMapping("{festivalId}/details/{passId}")
    public String displayDetails(@PathVariable Long festivalId,
                                            @PathVariable Long passId, Model model) {
        model.addAttribute("festivalId", festivalId);
        Optional<Pass> passOptional = passRepository.findById(passId);
        model.addAttribute("pass", passOptional.get());
        List<Event> events = passOptional.get().getEvents();
        model.addAttribute("events", events);

        return "pass/details";
    }


    @GetMapping("{festivalId}/edit/{passId}")
    public String editPass(@PathVariable Long festivalId,
                                @PathVariable Long passId, Model model){
        Optional<Pass> passOptional = passRepository.findById(passId);
        model.addAttribute("festivalId", festivalId);
        model.addAttribute("pass", passOptional.get());
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
        Optional<Festival> festivalOptional = festivalRepository.findById(festivalId);
        pass.setFestival(festivalOptional.get());
        passRepository.save(pass);
        return String.format("redirect:/festival/details/%s", festivalId);
    }

}