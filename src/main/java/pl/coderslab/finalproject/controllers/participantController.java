package pl.coderslab.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String displayAllParticipants(@PathVariable Long festivalId, Model model){
        Optional<Festival> festivalOptional = festivalRepository.findById(festivalId);
        List<Participant> participants = participantRepository.findAllByFestival(festivalOptional.get());
        model.addAttribute("festival", festivalOptional.get());
        model.addAttribute("participants", participants);

        return "/participant/all";
    }

    @GetMapping("/add")
    public String addParticipant(){
        return "/participant/add";
    }



    @GetMapping("/deleteConfirm/{festivalId}/{participantId}")
    public String deleteParticipantConfirmation(@PathVariable Long festivalId, @PathVariable Long participantId, Model model){
        Optional<Participant> participantOptional = participantRepository.findById(participantId);
        model.addAttribute("participant", participantOptional.get());
        model.addAttribute("festivalId", festivalId);
        return "/participant/delete";
    }

    @GetMapping("/delete/{festivalId}/{participantId}")
    public String deleteParticipant(@PathVariable Long participantId, @PathVariable Long festivalId){
        participantRepository.deleteById(participantId);
        return "redirect:/participant/all/" + festivalId;
    }


    @GetMapping("/edit")
    public String editParticipant(){
        return "/participant/add";
    }

    @GetMapping("")
    public String displayParticipant(){
        return "/participant/display";
    }

    @GetMapping("/addFromFile")
    public String addParticipantsFromFile(){
        return "/participant/addFromFile";
    }


}
