package pl.coderslab.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.models.merch.Merch;
import pl.coderslab.finalproject.models.person.Admin;
import pl.coderslab.finalproject.repositories.AdminRepository;
import pl.coderslab.finalproject.repositories.FestivalRepository;
import pl.coderslab.finalproject.repositories.MerchRepository;

import java.util.List;

@Controller
@RequestMapping("/main")
public class MainController {

    private final FestivalRepository festivalRepository;
    private final MerchRepository giftRepository;
    private final AdminRepository adminRepository;


    public MainController(FestivalRepository festivalRepository, MerchRepository giftRepository, AdminRepository adminRepository) {
        this.festivalRepository = festivalRepository;
        this.giftRepository = giftRepository;
        this.adminRepository = adminRepository;
    }

    @RequestMapping("")
    public String displayAllFestivals(Model model) {
        List<Festival> festivals = festivalRepository.findAll();
        model.addAttribute("festivals", festivals);
        List<Merch> gifts = giftRepository.findAll();
        model.addAttribute("gifts", gifts);
        List<Admin> admins = adminRepository.findAll();
        model.addAttribute("admins", admins);
        return "main/main";
    }


}
