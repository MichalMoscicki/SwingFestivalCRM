package pl.coderslab.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.models.festivalEvents.Event;
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

    @GetMapping("/{festivalId}/deleteConfirm/{eventId}")
    public String deleteEventConfirmation(@PathVariable Long festivalId, @PathVariable Long eventId, Model model) {
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        model.addAttribute("event", eventOptional.get());
        model.addAttribute("festivalId", festivalId);
        return "event/delete";
    }

    @GetMapping("/{festivalId}/delete/{eventId}")
    public String deleteParticipant(@PathVariable Long eventId, @PathVariable Long festivalId) {
        eventRepository.deleteById(eventId);
        return String.format("redirect:/festival/details/%s", festivalId);
    }


    @GetMapping("/edit")
    public String editEventDetails() {
        return "event/edit";
    }

    @GetMapping("/{festivalId}/eventTypeChoice")
    public String eventTypeChoice(@PathVariable Long festivalId, Model model) {
        model.addAttribute("festivalId", festivalId);
        return "event/typeChoice";
    }

    @PostMapping("/{festivalId}/eventTypeChoice")
    public String eventTypeChoice(@PathVariable Long festivalId, @RequestParam String eventType, Model model) {
        model.addAttribute("festivalId", festivalId);
        switch (eventType) {
            case "party":
                return String.format("redirect:/event/%s/addParty", festivalId );
            case "workshop":
                return String.format("redirect:/event/%s/addWorkshop", festivalId );
            case "specialEvent":
                return String.format("redirect:/event/%s/addSpecialEvent", festivalId );
        }
        return String.format("redirect:/festival/details/%s", festivalId );
    }

    @GetMapping("/{festivalId}/addParty")
    public String addParty(@PathVariable Long festivalId, Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("festivalId", festivalId);
        return "event/addParty";
    }

//    @PostMapping("/{festivalId}/addParty")
//    public String addParty(@Valid Party party, BindingResult result, @PathVariable Long festivalId) {
//        if (result.hasErrors()) {
//            return "event/addParty";
//        }
//        Optional<Festival> festivalOptional = festivalRepository.findById(festivalId);
//        party.setFestival(festivalOptional.get());
//        eventRepository.save(party);
//
//        return String.format("redirect:/festival/details/%s", festivalId);
//    }
//
    @GetMapping("/{festivalId}/addWorkshop")
    public String addWorkshop(@PathVariable Long festivalId, Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("festivalId", festivalId);
        return "event/addWorkshop";
    }
//
//    @PostMapping("/{festivalId}/addWorkshop")
//    public String addWorkshop(@Valid Workshop workshop, BindingResult result, @PathVariable Long festivalId) {
//        if (result.hasErrors()) {
//            return "event/addWorkshop";
//        }
//        Optional<Festival> festivalOptional = festivalRepository.findById(festivalId);
//        workshop.setFestival(festivalOptional.get());
//        eventRepository.save(workshop);
//        return String.format("redirect:/festival/details/%s", festivalId);
//    }
//
    @GetMapping("/{festivalId}/addSpecialEvent")
    public String addSpecialEvent(@PathVariable Long festivalId, Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("festivalId", festivalId);
        return "event/addSpecialEvent";
    }
//
//    @PostMapping("/{festivalId}/addSpecialEvent")
//    public String addSpecialEvent(@Valid SpecialEvent specialEvent, BindingResult result, @PathVariable Long festivalId) {
//        if (result.hasErrors()) {
//            return "event/addSpecialEvent";
//        }
//        Optional<Festival> festivalOptional = festivalRepository.findById(festivalId);
//        specialEvent.setFestival(festivalOptional.get());
//        eventRepository.save(specialEvent);
//        return String.format("redirect:/festival/details/%s", festivalId);
//    }
}
