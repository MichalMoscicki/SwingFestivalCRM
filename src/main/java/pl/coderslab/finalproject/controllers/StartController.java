package pl.coderslab.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.finalproject.models.person.Admin;
import pl.coderslab.finalproject.models.role.Role;
import pl.coderslab.finalproject.repositories.AdminRepository;
import pl.coderslab.finalproject.repositories.RoleRepository;
import pl.coderslab.finalproject.service.AdminService;

@Controller
public class StartController {



    @GetMapping("/")
    public String displayStartPage(){
        return "start/start";
    }

}
