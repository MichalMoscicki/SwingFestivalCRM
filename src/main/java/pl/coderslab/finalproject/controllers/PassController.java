package pl.coderslab.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
        List<Event> eventList = eventRepository.findAll();
        if (eventList.isEmpty()) {
            return String.format("redirect:/pass/%s/noEvents", festivalId);
        }
        model.addAttribute("events", eventList);
        return "pass/add";
    }

    @GetMapping("{festivalId}/noEvents")
    public String noEvents(@PathVariable Long festivalId, Model model) {
        model.addAttribute("festivalId", festivalId);
        return "noObject/noEvents";
    }

    @PostMapping("{festivalId}/add")
    public String addPass(@Valid Pass pass, BindingResult result,
                          @PathVariable Long festivalId, Model model) {
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
}
