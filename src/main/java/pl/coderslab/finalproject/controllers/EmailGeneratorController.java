package pl.coderslab.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.finalproject.models.emailGenerator.EmailGenerator;
import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.models.person.Participant;
import pl.coderslab.finalproject.repositories.FestivalRepository;
import pl.coderslab.finalproject.repositories.ParticipantRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/sendEmails")
public class EmailGeneratorController {
    private final ParticipantRepository participantRepository;
    private final FestivalRepository festivalRepository;
    private final EmailGenerator emailGenerator;


    public EmailGeneratorController(ParticipantRepository participantRepository, FestivalRepository festivalRepository, EmailGenerator emailGenerator) {
        this.participantRepository = participantRepository;
        this.festivalRepository = festivalRepository;
        this.emailGenerator = emailGenerator;
    }

    @GetMapping("/{festivalId}")
    public String sendEmails(@PathVariable Long festivalId, Model model){
        Optional<Festival> festivalOptional = festivalRepository.findById(festivalId);
        List<Participant> participants = participantRepository.findAllByFestival(festivalOptional.get());
        List<String> messages = new ArrayList<>();
        for(Participant participant : participants){
            messages.add(emailGenerator.generateInfoEmail(participant));

        }
        model.addAttribute("messages", messages);
        return "sendEmails/sendEmails";
    }

}
