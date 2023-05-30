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
import pl.coderslab.finalproject.service.AdminService;
import pl.coderslab.finalproject.service.MerchService;

import java.util.List;

@Controller
@RequestMapping("/main")
public class MainController {

    private final FestivalRepository festivalRepository;
    private final MerchService merchService;
    private final AdminService adminService;

    public MainController(FestivalRepository festivalRepository,
                          MerchService merchService,
                          AdminService adminService) {
        this.festivalRepository = festivalRepository;
        this.merchService = merchService;
        this.adminService = adminService;
    }

    @RequestMapping("")
    public String displayAllFestivals(Model model) {
        List<Festival> festivals = festivalRepository.findAll();
        model.addAttribute("festivals", festivals);
        List<Merch> merch = merchService.findAll();
        model.addAttribute("merch", merch);
        List<Admin> admins = adminService.findAll();
        model.addAttribute("admins", admins);
        return "main/main";
    }


}
