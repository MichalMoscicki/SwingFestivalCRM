package pl.coderslab.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.models.gift.Gift;
import pl.coderslab.finalproject.repositories.FestivalRepository;
import pl.coderslab.finalproject.repositories.GiftRepository;

import java.util.List;

@Controller
@RequestMapping("/main")
public class MainController {

    private FestivalRepository festivalRepository;
    private GiftRepository giftRepository;

    public MainController(FestivalRepository festivalRepository, GiftRepository giftRepository) {
        this.festivalRepository = festivalRepository;
        this.giftRepository = giftRepository;
    }

    @GetMapping("")
    public String displayAllFestivals(Model model) {
        List<Festival> festivals = festivalRepository.findAll();
        model.addAttribute("festivals", festivals);
        List<Gift> gifts = giftRepository.findAll();
        model.addAttribute("gifts", gifts);
        return "main/main";
    }


}
