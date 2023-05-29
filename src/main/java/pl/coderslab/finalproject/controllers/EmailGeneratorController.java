package pl.coderslab.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.finalproject.service.FestivalService;
import pl.coderslab.finalproject.utils.email.EmailGenerator;
import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.models.person.Participant;
import pl.coderslab.finalproject.repositories.FestivalRepository;
import pl.coderslab.finalproject.repositories.ParticipantRepository;
import pl.coderslab.finalproject.utils.email.EmailSender;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/sendEmails")
public class EmailGeneratorController {
    private final ParticipantRepository participantRepository;
    private final FestivalService festivalService;
    private final EmailGenerator emailGenerator;
    private final EmailSender emailSender;


    public EmailGeneratorController(ParticipantRepository participantRepository,
                                    FestivalService festivalService,
                                    EmailGenerator emailGenerator,
                                    EmailSender emailSender) {
        this.participantRepository = participantRepository;
        this.festivalService = festivalService;
        this.emailGenerator = emailGenerator;
        this.emailSender = emailSender;
    }

    @GetMapping("/{festivalId}")
    public String sendEmails(@PathVariable Long festivalId, Model model) throws MessagingException, GeneralSecurityException, IOException {
        int emailsSent = 0;
        Festival festival = festivalService.findFestival(festivalId);
        List<Participant> participants = participantRepository.findAllByFestival(festival);
        String messageSubject = String.format("%s - informacje", festival.getName());
        for(Participant participant : participants){
            emailSender.sendEmail(participant.getEmail(),messageSubject, emailGenerator.generateInfoEmail(participant));
            emailsSent++;
        }
        model.addAttribute("emailsSent", emailsSent);
        return "sendEmails/sendEmails";
    }

}
