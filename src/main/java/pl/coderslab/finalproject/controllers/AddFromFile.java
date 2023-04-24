package pl.coderslab.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.models.parser.Parser;
import pl.coderslab.finalproject.models.person.Participant;
import pl.coderslab.finalproject.repositories.FestivalRepository;
import pl.coderslab.finalproject.repositories.ParticipantRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/addFromFile")
public class AddFromFile {
    private final Parser parser;
    private final FestivalRepository festivalRepository;
    private final ParticipantRepository participantRepository;

    public AddFromFile(Parser parser, FestivalRepository festivalRepository, ParticipantRepository participantRepository) {
        this.parser = parser;
        this.festivalRepository = festivalRepository;
        this.participantRepository = participantRepository;
    }

    @GetMapping("/{festivalId}/chooseFile")
    public String displayChooseForm(@PathVariable Long festivalId, Model model) {
        model.addAttribute("festivalId", festivalId);
        return "addFromFile/chooseFile";
    }

    @PostMapping("/{festivalId}/chooseFile")
    @ResponseBody
    public String displayChooseForm(@RequestParam String filePath) {
        return filePath;
        // return "addFromFile/chooseFile";
    }

    @GetMapping("{festivalId}/result")
    public String result(@PathVariable Long festivalId,
                         Model model) {
        Optional<Festival> festivalOptional = festivalRepository.findById(festivalId);
        List<Participant> participantList = parser.parseFile(
                "/Users/michalmoscicki/Documents/CodersLab/finalProject/src/main/resources/SPL.csv", festivalOptional.get());
        int participantsAdded = 0;

        for (Participant participant : participantList) {
            participantsAdded++;
            participantRepository.save(participant);
        }
        model.addAttribute("participantsAdded", participantsAdded);
        model.addAttribute("festivalId", festivalId);
        return "addFromFile/result";
    }


}
