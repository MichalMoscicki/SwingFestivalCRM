package pl.coderslab.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.finalproject.models.person.Admin;
import pl.coderslab.finalproject.repositories.AdminRepository;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminRepository adminRepository;

    public AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @GetMapping("add")
    public String addAdmin(Model model){
        model.addAttribute("admin", new Admin());
        return "admin/add";
    }

    @PostMapping("add")
    public String addAdmin(@Valid Admin admin, BindingResult result){
        if (result.hasErrors()){
            return "admin/add";
        }
        adminRepository.save(admin);
        return "redirect:/main";
    }

    @GetMapping("/deleteConfirm/{adminId}")
    public String deleteConfirm(@PathVariable Long adminId, Model model){
        Optional<Admin> adminOptional = adminRepository.findById(adminId);
        model.addAttribute("admin", adminOptional.get());
        return "admin/delete";

    }

    @GetMapping("/delete/{adminId}")
    public String delete(@PathVariable Long adminId){
        Optional<Admin> adminOptional = adminRepository.findById(adminId);
        adminRepository.delete(adminOptional.get());
        return "redirect:/main";

    }

    @GetMapping("edit/{adminId}")
    public String editAdmin(@PathVariable Long adminId, Model model){
        Optional<Admin> adminOptional = adminRepository.findById(adminId);
        model.addAttribute("admin", adminOptional.get());
        return "admin/edit";
    }

    @PostMapping("edit/{adminId}")
    public String editAdmin(@Valid Admin admin, BindingResult result){
        if (result.hasErrors()){
            return "admin/add";
        }
        adminRepository.save(admin);
        return "redirect:/main";
    }

}
