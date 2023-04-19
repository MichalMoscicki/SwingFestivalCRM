package pl.coderslab.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.models.festivalEvents.Party;
import pl.coderslab.finalproject.models.festivalEvents.Workshop;
import pl.coderslab.finalproject.repositories.EventRepository;
import pl.coderslab.finalproject.repositories.FestivalRepository;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/event")
public class EventController {

   private FestivalRepository festivalRepository;
   private EventRepository eventRepository;

    public EventController(FestivalRepository festivalRepository, EventRepository eventRepository) {
        this.festivalRepository = festivalRepository;
        this.eventRepository = eventRepository;
    }

    @GetMapping("/details")
    public String displayEventDetails() {
        return "event/details";
    }

    @GetMapping("/delete")
    public String deleteEvent() {
        return "event/delete";
    }

    @GetMapping("/edit")
    public String editEventDetails() {
        return "event/edit";
    }

    @GetMapping("/{festivalId}/addParty")
    public String addParty(@PathVariable Long festivalId, Model model) {
        model.addAttribute("party", new Party());
        model.addAttribute("festivalId", festivalId);
        return "event/addParty";
    }

    @PostMapping("/{festivalId}/addParty")
    public String addParty(@Valid Party party, BindingResult result, @PathVariable Long festivalId) {
        if(result.hasErrors()){
            return "event/addParty";
        }
        Optional<Festival> festivalOptional = festivalRepository.findById(festivalId);
        party.setFestival(festivalOptional.get());
        eventRepository.save(party);

        return String.format ("redirect:/festival/details/%s", festivalId);
    }

    @GetMapping("/{fesitivalId}/addWorkshop")
    public String addWorkshop(@PathVariable Long festivalId, Model model) {
        model.addAttribute("workshop", new Workshop());
        model.addAttribute("festivalId", festivalId);
        return "event/addWorkshop";
    }

    @GetMapping("/{fesitivalId}/addSpecialEvent")
    public String addSpecialEvent() {
        return "event/addSpecialEvent";
    }


}
