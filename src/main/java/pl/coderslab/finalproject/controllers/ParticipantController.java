package pl.coderslab.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.models.event.Event;
import pl.coderslab.finalproject.models.gift.Gift;
import pl.coderslab.finalproject.models.person.Participant;
import pl.coderslab.finalproject.repositories.EventRepository;
import pl.coderslab.finalproject.repositories.FestivalRepository;
import pl.coderslab.finalproject.repositories.GiftRepository;
import pl.coderslab.finalproject.repositories.ParticipantRepository;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/participant")
public class ParticipantController {

    ParticipantRepository participantRepository;
    FestivalRepository festivalRepository;
    GiftRepository giftRepository;
    EventRepository eventRepository;

    public ParticipantController(ParticipantRepository participantRepository, FestivalRepository festivalRepository,
                                 GiftRepository giftRepository, EventRepository eventRepository) {
        this.participantRepository = participantRepository;
        this.festivalRepository = festivalRepository;
        this.giftRepository = giftRepository;
        this.eventRepository = eventRepository;
    }

    @GetMapping("/all/{festivalId}")
    public String displayAllParticipants(@PathVariable Long festivalId, Model model) {
        Optional<Festival> festivalOptional = festivalRepository.findById(festivalId);
        List<Participant> participants = participantRepository.findAllByFestival(festivalOptional.get());
        model.addAttribute("festival", festivalOptional.get());
        model.addAttribute("participants", participants);

        return "/participant/all";
    }

    @GetMapping("{festivalId}/add")
    public String displayForm(@PathVariable Long festivalId, Model model) {
        List<Gift> gifts = giftRepository.findAll();
        List<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);
        model.addAttribute("gifts", gifts);
        model.addAttribute("festivalId", festivalId);
        model.addAttribute("participant", new Participant());
        return "/participant/add";
    }

    @PostMapping("{festivalId}/add")
    public String addParticipant(@Valid Participant participant, BindingResult result,
                                 @PathVariable Long festivalId) {
        if (result.hasErrors()) {
            return String.format("redirect:/participant/%s/add", festivalId);
        }
        participant.setRegistrationDate(LocalDateTime.now());
        Optional<Festival> festivalOptional = festivalRepository.findById(festivalId);
        participant.setFestival(festivalOptional.get());
        BigDecimal price = new BigDecimal("0.00");
        for (Gift gift : participant.getGifts()) {
            price = price.add(gift.getPrice());
        }
//        for (Event event : participant.getEvents()) {
//            price = price.add(event.getPrice());
//        }
        //popraw tu!
        participant.setAmountToPay(price);
        participantRepository.save(participant);
        return String.format("redirect:/festival/details/%s", festivalId);
    }

    @GetMapping("/addFromFile")
    public String addParticipantsFromFile() {
        return "/participant/addFromFile";
    }


    @GetMapping("/deleteConfirm/{festivalId}/{participantId}")
    public String deleteParticipantConfirmation(@PathVariable Long festivalId, @PathVariable Long participantId, Model model) {
        Optional<Participant> participantOptional = participantRepository.findById(participantId);
        model.addAttribute("participant", participantOptional.get());
        model.addAttribute("festivalId", festivalId);
        return "/participant/delete";
    }

    @GetMapping("/delete/{festivalId}/{participantId}")
    public String deleteParticipant(@PathVariable Long participantId, @PathVariable Long festivalId) {
        participantRepository.deleteById(participantId);
        return "redirect:/participant/all/" + festivalId;
    }

    @GetMapping("{festivalId}/edit/{participantId}")
    public String displayEditParticipant(@PathVariable Long festivalId,
                                         @PathVariable Long participantId, Model model) {
        Optional<Participant> participant = participantRepository.findById(participantId);
        model.addAttribute("participant", participant.get());
        model.addAttribute("festivalId", festivalId);
        return "/participant/edit";
    }

    @GetMapping("{festivalId}/details/{participantId}")
    public String displayParticipant(@PathVariable Long participantId, @PathVariable Long festivalId, Model model) {
        Optional<Participant> participantOptional = participantRepository.findById(participantId);
        model.addAttribute("participant", participantOptional.get());
        model.addAttribute("festivalId", festivalId);
        return "/participant/details";
    }

    @PostMapping("/{festivalId}/findByEmail")
    public String findParticipantByEmail(@RequestParam("email") String email, @PathVariable Long festivalId) {
        Participant participant = participantRepository.findByEmail(email);

        if (participant == null) {
            return String.format("redirect:/participant/%s/notFound/%s", festivalId, email);
        }
        return String.format("redirect:details/%s", participant.getId());
    }

    @PostMapping("/{festivalId}/findByLastName")
    public String findParticipantsByLastName(@RequestParam("lastName") String lastName, @PathVariable Long festivalId, Model model) {
        List<Participant> participants = participantRepository.findAllByLastName(lastName);
        model.addAttribute("participants", participants);
        model.addAttribute("lastName", lastName);
        model.addAttribute("festivalId", festivalId);

        if (participants.isEmpty()) {
            return String.format("redirect:/participant/%s/notFound/%s", festivalId, lastName);
        }
        return "participant/listByLastName";
    }

    @GetMapping("{festivalId}/notFound/{lookedPhrase}")
    public String participantNotFound(@PathVariable Long festivalId,
                                      @PathVariable String lookedPhrase, Model model) {
        model.addAttribute("festivalId", festivalId);
        model.addAttribute("lookedPhrase", lookedPhrase);
        return "participant/notFound";
    }

    @GetMapping("{festivalId}/{participantId}/confirmPayment")
    public String confirmPayment(@PathVariable Long festivalId,
                                 @PathVariable Long participantId) {
        Optional<Participant> participantOptional = participantRepository.findById(participantId);
        participantOptional.get().setAlreadyPaid(true);
        participantRepository.save(participantOptional.get());
        return (String.format("redirect:/participant/%s/details/%s", festivalId, participantId));
    }

    @GetMapping("{festivalId}/{participantId}/giveBracelet")
    public String giveBracelet(@PathVariable Long festivalId,
                               @PathVariable Long participantId) {
        Optional<Participant> participantOptional = participantRepository.findById(participantId);
        participantOptional.get().setBraceletGiven(true);
        participantRepository.save(participantOptional.get());
        return (String.format("redirect:/participant/%s/details/%s", festivalId, participantId));
    }

    @GetMapping("{festivalId}/{participantId}/giveMerch")
    public String giveMerch(@PathVariable Long festivalId,
                            @PathVariable Long participantId) {
        Optional<Participant> participantOptional = participantRepository.findById(participantId);
        participantOptional.get().setGiftsGiven(true);
        participantRepository.save(participantOptional.get());
        return (String.format("redirect:/participant/%s/details/%s", festivalId, participantId));
    }

    @GetMapping("{festivalId}/deleteParticipantFromEventConfirm/{participantId}/{eventId}")
    public String deleteParticipantFromEventConfirm(@PathVariable Long festivalId,
                                        @PathVariable Long participantId,
                                        @PathVariable Long eventId, Model model){
        Optional<Participant> participantOptional = participantRepository.findById(participantId);
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        model.addAttribute("participant", participantOptional.get());
        model.addAttribute("event", eventOptional.get());
        model.addAttribute("festivalId", festivalId);
       return "event/deleteParticipantFromEventConfirm";
    }

    @GetMapping("{festivalId}/deleteParticipantFromEvent/{participantId}/{eventId}")
        public String deleteParticipantFromEvent(@PathVariable Long festivalId,
                                                    @PathVariable Long participantId,
                                                    @PathVariable Long eventId){
        Optional<Participant> participantOptional = participantRepository.findById(participantId);
        Optional<Event> eventOptional = eventRepository.findById(eventId);
     //   List<Event> participantEvents =  participantOptional.get().getEvents();
//        participantEvents.remove(eventOptional.get());
//        participantOptional.get().setEvents(participantEvents);
        participantRepository.save(participantOptional.get());
        return String.format("redirect:/event/%s/details/%s", festivalId, eventId);
    }



}
