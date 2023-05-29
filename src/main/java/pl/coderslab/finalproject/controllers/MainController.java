package pl.coderslab.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.models.gift.Gift;
import pl.coderslab.finalproject.models.person.Admin;
import pl.coderslab.finalproject.models.role.Role;
import pl.coderslab.finalproject.repositories.AdminRepository;
import pl.coderslab.finalproject.repositories.FestivalRepository;
import pl.coderslab.finalproject.repositories.GiftRepository;

import java.util.List;

@Controller
@RequestMapping("/main")
public class MainController {

    private final FestivalRepository festivalRepository;
    private final GiftRepository giftRepository;
    private final AdminRepository adminRepository;


    public MainController(FestivalRepository festivalRepository, GiftRepository giftRepository, AdminRepository adminRepository) {
        this.festivalRepository = festivalRepository;
        this.giftRepository = giftRepository;
        this.adminRepository = adminRepository;
    }

    @RequestMapping("")
    public String displayAllFestivals(Model model) {
        List<Festival> festivals = festivalRepository.findAll();
        model.addAttribute("festivals", festivals);
        List<Gift> gifts = giftRepository.findAll();
        model.addAttribute("gifts", gifts);
        List<Admin> admins = adminRepository.findAll();
        model.addAttribute("admins", admins);
        return "main/main";
    }


}
