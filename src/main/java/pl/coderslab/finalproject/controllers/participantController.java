package pl.coderslab.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/participant")
public class participantController {

    @GetMapping("/all")
    public String displayAllParticipants(){
        return "/participant/all";
    }

    @GetMapping("/add")
    public String addParticipant(){
        return "/participant/add";
    }

    @GetMapping("/delete")
    public String deleteParticipant(){
        return "/participant/delete";
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
