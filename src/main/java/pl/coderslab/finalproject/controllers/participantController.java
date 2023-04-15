package pl.coderslab.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.models.person.Participant;
import pl.coderslab.finalproject.repositories.FestivalRepository;
import pl.coderslab.finalproject.repositories.ParticipantRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/participant")
public class participantController {

    ParticipantRepository participantRepository;
    FestivalRepository festivalRepository;

    public participantController(ParticipantRepository participantRepository, FestivalRepository festivalRepository) {
        this.participantRepository = participantRepository;
        this.festivalRepository = festivalRepository;
    }

    @GetMapping("/all/{festivalId}")
    public String displayAllParticipants(@PathVariable Long festivalId, Model model) {
        Optional<Festival> festivalOptional = festivalRepository.findById(festivalId);
        List<Participant> participants = participantRepository.findAllByFestival(festivalOptional.get());
        model.addAttribute("festival", festivalOptional.get());
        model.addAttribute("participants", participants);

        return "/participant/all";
    }

    @GetMapping("/add")
    public String addParticipant() {
        return "/participant/add";
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

    @GetMapping("/edit")
    public String editParticipant() {
        return "/participant/add";
    }

    @GetMapping("{festivalId}/details/{participantId}")
    public String displayParticipant(@PathVariable Long participantId, @PathVariable Long festivalId, Model model) {
        Optional<Participant> participantOptional = participantRepository.findById(participantId);
        model.addAttribute("participant", participantOptional.get());
        model.addAttribute("festivalId", festivalId);
        return "/participant/details";
    }

    @GetMapping("/addFromFile")
    public String addParticipantsFromFile() {
        return "/participant/addFromFile";
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
    public String participantNotFound(@PathVariable Long festivalId, Model model, @PathVariable String lookedPhrase) {
        model.addAttribute("festivalId", festivalId);
        model.addAttribute("lookedPhrase", lookedPhrase);
        return "participant/notFound";
    }

}
