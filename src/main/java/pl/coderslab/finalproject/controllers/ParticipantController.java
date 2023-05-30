package pl.coderslab.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.models.event.Event;
import pl.coderslab.finalproject.models.merch.Merch;
import pl.coderslab.finalproject.models.pass.Pass;
import pl.coderslab.finalproject.models.person.Participant;
import pl.coderslab.finalproject.repositories.*;
import pl.coderslab.finalproject.service.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/participant")
public class ParticipantController {

    private final ParticipantService participantService;
    private final FestivalService festivalService;
    private final MerchService merchService;
    private final EventService eventService;
    private final PassService passService;


    public ParticipantController(ParticipantService participantService,
                                 FestivalService festivalService,
                                 MerchService merchService,
                                 EventService eventService,
                                 PassService passService) {
        this.participantService = participantService;
        this.festivalService = festivalService;
        this.merchService = merchService;
        this.eventService = eventService;
        this.passService = passService;
    }

    @GetMapping("/all/{festivalId}")
    public String displayAllParticipants(@PathVariable Long festivalId, Model model) {
        Festival festival = festivalService.findFestival(festivalId);
        List<Participant> participants = participantService.findAllByFestival(festival);
        model.addAttribute("festival", festival);
        model.addAttribute("participants", participants);

        return "/participant/all";
    }

    @GetMapping("{festivalId}/add")
    public String displayAddForm(@PathVariable Long festivalId, Model model) {
        Festival festival = festivalService.findFestival(festivalId);
        List<Merch> merch = merchService.findAll();
        List<Pass> passes = passService.findAllByFestival(festival);
        if (passes.isEmpty()) {
            return String.format("redirect:/%s/noPass", festivalId);
        }
        model.addAttribute("passes", passes);
        model.addAttribute("merch", merch);
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
        Festival festival = festivalService.findFestival(festivalId);
        participant.setFestival(festival);
        BigDecimal price = new BigDecimal("0.00");
        for (Merch gift : participant.getMerch()) {
            price = price.add(gift.getPrice());
        }
        for (Pass pass : participant.getPasses()) {
            price = price.add(pass.getPrice());
        }
        participant.setAmountToPay(price);
        participantService.add(participant);
        return String.format("redirect:/festival/details/%s", festivalId);
    }

    @GetMapping("/deleteConfirm/{festivalId}/{participantId}")
    public String deleteParticipantConfirmation(@PathVariable Long festivalId,
                                                @PathVariable Long participantId,
                                                Model model) {
        Participant participant = participantService.findById(participantId);
        model.addAttribute("participant", participant);
        model.addAttribute("festivalId", festivalId);
        return "/participant/delete";
    }

    @GetMapping("/delete/{festivalId}/{participantId}")
    public String deleteParticipant(@PathVariable Long participantId,
                                    @PathVariable Long festivalId) {
        passService.delete(participantId);
        return "redirect:/participant/all/" + festivalId;
    }

    @GetMapping("{festivalId}/edit/{participantId}")
    public String editParticipant(@PathVariable Long festivalId,
                                  @PathVariable Long participantId, Model model) {
        List<Merch> merch = merchService.findAll();
        List<Pass> passes = passService.findAll();
        Participant participant = participantService.findById(participantId);
        model.addAttribute("participant", participant);
        model.addAttribute("festivalId", festivalId);
        model.addAttribute("passes", passes);
        model.addAttribute("gifts", merch);
        return "/participant/edit";
    }

    @PostMapping("/{festivalId}/edit/{participantId}")
    public String editParticipant(@Valid Participant participant, BindingResult result,
                                  @PathVariable Long participantId,
                                  @PathVariable Long festivalId) {
        if (result.hasErrors()) {
            System.out.println(result.getFieldError());
            return String.format("redirect:/participant/%s/edit/%s", festivalId, participantId);
        }
        Festival festival = festivalService.findFestival(festivalId);
        participant.setFestival(festival);

        BigDecimal price = new BigDecimal("0.00");
        for (Merch gift : participant.getMerch()) {
            price = price.add(gift.getPrice());
        }
        for (Pass pass : participant.getPasses()) {
            price = price.add(pass.getPrice());
        }
        participant.setAmountToPay(price);
        participantService.add(participant);
        return String.format("redirect:/festival/details/%s", festivalId);
    }

    @GetMapping("{festivalId}/details/{participantId}")
    public String displayParticipantDetails(@PathVariable Long participantId, @PathVariable Long festivalId, Model model) {
        Participant participant = participantService.findById(participantId);
        model.addAttribute("participant", participant);
        model.addAttribute("festivalId", festivalId);
        return "/participant/details";
    }

    @PostMapping("/{festivalId}/findByEmail")
    public String findParticipantByEmail(@RequestParam("email") String email, @PathVariable Long festivalId) {
        Participant participant = participantService.findByEmailIgnoreCase(email);

        if (participant == null) {
            return String.format("redirect:/participant/%s/notFound/%s", festivalId, email.trim());
        }
        return String.format("redirect:details/%s", participant.getId());
    }

    @PostMapping("/{festivalId}/findByLastName")
    public String findParticipantsByLastName(@RequestParam("lastName") String lastName, @PathVariable Long festivalId, Model model) {
        List<Participant> participants = participantService.findAllByLastNameIgnoreCase(lastName);
        model.addAttribute("participants", participants);
        model.addAttribute("lastName", lastName.trim());
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
        Participant participant = participantService.findById(participantId);
        participant.setAlreadyPaid(true);
        participantService.add(participant);
        return (String.format("redirect:/participant/%s/details/%s", festivalId, participantId));
    }

    @GetMapping("{festivalId}/{participantId}/giveBracelet")
    public String giveBracelet(@PathVariable Long festivalId,
                               @PathVariable Long participantId) {
        Participant participant = participantService.findById(participantId);
        participant.setBraceletGiven(true);
        participantService.add(participant);
        return (String.format("redirect:/participant/%s/details/%s", festivalId, participantId));
    }

    @GetMapping("{festivalId}/{participantId}/giveMerch")
    public String giveMerch(@PathVariable Long festivalId,
                            @PathVariable Long participantId) {
        Participant participant = participantService.findById(participantId);
        participant.setGiftsGiven(true);
        participantService.add(participant);
        return (String.format("redirect:/participant/%s/details/%s", festivalId, participantId));
    }

    @GetMapping("{festivalId}/deleteParticipantFromEventConfirm/{participantId}/{eventId}")
    public String deleteParticipantFromEventConfirm(@PathVariable Long festivalId,
                                                    @PathVariable Long participantId,
                                                    @PathVariable Long eventId, Model model) {
        Participant participant = participantService.findById(participantId);
        Event event = eventService.findById(eventId);
        model.addAttribute("participant", participant);
        model.addAttribute("event", event);
        model.addAttribute("festivalId", festivalId);
        return "event/deleteParticipantFromEventConfirm";
    }

}
