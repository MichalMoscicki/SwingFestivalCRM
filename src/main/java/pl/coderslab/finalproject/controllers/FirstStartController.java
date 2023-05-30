package pl.coderslab.finalproject.controllers;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.finalproject.models.person.Admin;
import pl.coderslab.finalproject.models.role.Role;
import pl.coderslab.finalproject.repositories.AdminRepository;
import pl.coderslab.finalproject.repositories.RoleRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class FirstStartController {
    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public FirstStartController(AdminRepository adminRepository,
                                RoleRepository roleRepository,
                                BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.adminRepository = adminRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("/firstStart")
    @ResponseBody
    public String firstStart(){

        Role role = new Role();
        role.setName("ROLE_ADMIN");
        roleRepository.save(role);

        Admin admin = new Admin();
        admin.setEmail("karol@karolak.pl");
        admin.setFirstName("Karol");
        admin.setLastName("Karolak");
        admin.setPassword(bCryptPasswordEncoder.encode("karol"));

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        admin.setRoles(roles);
        adminRepository.save(admin);

        return "Dodano pierwszego admina";
    }

}
