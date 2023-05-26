package pl.coderslab.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.finalproject.models.person.Admin;
import pl.coderslab.finalproject.service.AdminService;

@Controller
public class TempController{

    private final  AdminService adminService;

    public TempController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/FirtsUser")
        @ResponseBody
        public String createUser() {
            Admin admin = new Admin();
            admin.setFirstName("Karol");
            admin.setLastName("Karolak");
            admin.setEmail("karol@karolak.pl");
            admin.setPassword("karol");

            adminService.saveUser(admin);
            return "AdminAdded";
        }
}
