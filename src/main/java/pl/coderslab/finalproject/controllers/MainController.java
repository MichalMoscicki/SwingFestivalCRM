package pl.coderslab.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.models.merch.Merch;
import pl.coderslab.finalproject.models.person.Admin;
import pl.coderslab.finalproject.repositories.FestivalRepository;
import pl.coderslab.finalproject.service.AdminService;
import pl.coderslab.finalproject.service.FestivalService;
import pl.coderslab.finalproject.service.MerchService;

import java.util.List;

@Controller
@RequestMapping("/main")
public class MainController {

    private final FestivalService festivalService;
    private final MerchService merchService;
    private final AdminService adminService;

    public MainController(FestivalService festivalService,
                          MerchService merchService,
                          AdminService adminService) {
        this.festivalService = festivalService;
        this.merchService = merchService;
        this.adminService = adminService;
    }

    @RequestMapping("")
    public String displayAllFestivals(Model model) {
        Festival recentlyAddedFestival = festivalService.findRecenltyAddedFestival();
        model.addAttribute("recentlyAddedFestival", recentlyAddedFestival);
        return "/main";
    }


}
