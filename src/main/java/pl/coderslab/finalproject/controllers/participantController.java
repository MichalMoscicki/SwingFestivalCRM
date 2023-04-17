package pl.coderslab.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.models.gift.Gift;
import pl.coderslab.finalproject.models.person.Participant;
import pl.coderslab.finalproject.repositories.FestivalRepository;
import pl.coderslab.finalproject.repositories.GiftRepository;
import pl.coderslab.finalproject.repositories.ParticipantRepository;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/participant")
public class participantController {

    ParticipantRepository participantRepository;
    FestivalRepository festivalRepository;
    GiftRepository giftRepository;

    public participantController(ParticipantRepository participantRepository, FestivalRepository festivalRepository, GiftRepository giftRepository) {
        this.participantRepository = participantRepository;
        this.festivalRepository = festivalRepository;
        this.giftRepository = giftRepository;
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
        model.addAttribute("gifts", gifts);
        model.addAttribute("festivalId", festivalId);
        model.addAttribute("participant", new Participant());
        return "/participant/add";
    }


    @PostMapping("{festivalId}/add")
    public String addParticipant(@Valid Participant participant, BindingResult result,
                                 @PathVariable Long festivalId) {
        if(result.hasErrors()){
            return String.format("redirect:/participant/%s/add", festivalId);
        }
        participant.setRegistrationDate(LocalDateTime.now());
        Optional<Festival> festivalOptional= festivalRepository.findById(festivalId);
        participant.setFestival(festivalOptional.get());
        participantRepository.save(participant);
        return String.format("redirect:/festival/details/%s", festivalId);
    }



    //-----------//////
    @GetMapping("/addFromFile")
    public String addParticipantsFromFile() {
        return "/participant/addFromFile";
    }
//-----------//////

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

    //-----------//////
    @GetMapping("/edit")
    public String editParticipant() {
        return "/participant/add";
    }
    //-----------//////

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
                                 @PathVariable Long participantId){
        Optional<Participant> participantOptional = participantRepository.findById(participantId);
        participantOptional.get().setAlreadyPaid(true);
        participantRepository.save(participantOptional.get());
        return (String.format("redirect:/participant/%s/details/%s", festivalId, participantId));
    }

    @GetMapping("{festivalId}/{participantId}/giveBracelet")
    public String giveBracelet(@PathVariable Long festivalId,
                                 @PathVariable Long participantId){
        Optional<Participant> participantOptional = participantRepository.findById(participantId);
        participantOptional.get().setBraceletGiven(true);
        participantRepository.save(participantOptional.get());
        return (String.format("redirect:/participant/%s/details/%s", festivalId, participantId));
    }

    @GetMapping("{festivalId}/{participantId}/giveMerch")
    public String giveMerch(@PathVariable Long festivalId,
                               @PathVariable Long participantId){
        Optional<Participant> participantOptional = participantRepository.findById(participantId);
        participantOptional.get().setGiftsGiven(true);
        participantRepository.save(participantOptional.get());
        return (String.format("redirect:/participant/%s/details/%s", festivalId, participantId));
    }


}
