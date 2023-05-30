package pl.coderslab.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.models.event.Event;
import pl.coderslab.finalproject.models.person.Participant;
import pl.coderslab.finalproject.repositories.EventRepository;
import pl.coderslab.finalproject.repositories.FestivalRepository;
import pl.coderslab.finalproject.repositories.ParticipantRepository;
import pl.coderslab.finalproject.service.EventService;
import pl.coderslab.finalproject.service.FestivalService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/event")
public class EventController {

    private final FestivalService festivalService;
    private final EventService eventService;
    private final EventRepository eventRepository;


    public EventController(FestivalService festivalService,
                           EventService eventService,
                           EventRepository eventRepository) {
        this.festivalService = festivalService;
        this.eventService = eventService;
        this.eventRepository = eventRepository;
    }

    @GetMapping("/{festivalId}/details/{eventId}")
    public String displayEventDetails(@PathVariable Long festivalId,
                                      @PathVariable long eventId,
                                      Model model) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        model.addAttribute("event", optionalEvent.get());
        model.addAttribute("festivalId", festivalId);
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
        eventService.delete(eventId);
        return String.format("redirect:/festival/details/%s", festivalId);
    }

    @GetMapping("/{festivalId}/eventTypeChoice")
    public String eventTypeChoice(@PathVariable Long festivalId, Model model) {
        model.addAttribute("festivalId", festivalId);
        return "event/typeChoice";
    }

    @PostMapping("/{festivalId}/eventTypeChoice")
    public String eventTypeChoice(@PathVariable Long festivalId, @RequestParam String eventType, Model model) {
        model.addAttribute("festivalId", festivalId);
        return switch (eventType) {
            case "party" -> String.format("redirect:/event/%s/addParty", festivalId);
            case "workshop" -> String.format("redirect:/event/%s/addWorkshop", festivalId);
            case "specialEvent" -> String.format("redirect:/event/%s/addSpecialEvent", festivalId);
            default -> String.format("redirect:/festival/details/%s", festivalId);
        };
    }

    @RequestMapping("/{festivalId}/addParty")
    public String addParty(@PathVariable Long festivalId, Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("festivalId", festivalId);
        return "event/addParty";
    }

    @PostMapping("/{festivalId}/addParty")
    public String addParty(@Valid Event event, BindingResult result, @PathVariable Long festivalId) {
        if (result.hasErrors()) {
            //czy to nie powinno pójśc do logów?
            System.out.println(result.getFieldError());
            return "event/addParty";
        }
        Festival festival = festivalService.findFestival(festivalId);
        event.setFestival(festival);
        eventRepository.save(event);
        return String.format("redirect:/festival/details/%s", festivalId);
    }

    @GetMapping("/{festivalId}/addWorkshop")
    public String addWorkshop(@PathVariable Long festivalId, Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("festivalId", festivalId);
        return "event/addWorkshop";
    }

    @PostMapping("/{festivalId}/addWorkshop")
    public String addWorkshop(@Valid Event event, BindingResult result, @PathVariable Long festivalId) {
        if (result.hasErrors()) {
            return "event/addWorkshop";
        }
        Festival festival = festivalService.findFestival(festivalId);
        event.setFestival(festival);
        eventRepository.save(event);
        return String.format("redirect:/festival/details/%s", festivalId);
    }

    @GetMapping("/{festivalId}/addSpecialEvent")
    public String addSpecialEvent(@PathVariable Long festivalId, Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("festivalId", festivalId);
        return "event/addSpecialEvent";
    }

    @PostMapping("/{festivalId}/addSpecialEvent")
    public String addSpecialEvent(@Valid Event event, BindingResult result, @PathVariable Long festivalId) {
        if (result.hasErrors()) {
            return "event/addSpecialEvent";
        }
        Festival festival = festivalService.findFestival(festivalId);
        event.setFestival(festival);
        eventRepository.save(event);
        return String.format("redirect:/festival/details/%s", festivalId);
    }


    @GetMapping("/{festivalId}/edit/{eventId}")
    public String editEventDetails(@PathVariable Long festivalId,
                                   @PathVariable Long eventId,
                                   Model model) {
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        model.addAttribute("event", eventOptional.get());
        return "event/edit";
    }

   @PostMapping("/{festivalId}/edit/{eventId}")
   public String updateEvent(@Valid Event event,
                             BindingResult result,
                             @PathVariable Long festivalId,
                             @PathVariable Long eventId){
        if(result.hasErrors()){
            return String.format("redirect:/event/%s/edit/%s", festivalId, eventId);
        }
       Festival festival = festivalService.findFestival(festivalId);
       event.setFestival(festival);
        eventRepository.save(event);
       return String.format("redirect:/festival/details/%s", festivalId);
   }
}
