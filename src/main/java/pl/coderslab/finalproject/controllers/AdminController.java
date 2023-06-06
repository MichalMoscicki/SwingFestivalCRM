package pl.coderslab.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.finalproject.models.person.Admin;
import pl.coderslab.finalproject.service.AdminService;


import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("")
    public String displayAll(Model model) {
        List<Admin> admins = adminService.findAll();
        model.addAttribute("admins", admins);
        return "adminAll";
    }

    @GetMapping("add")
    public String addAdmin(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin/add";
    }

    @PostMapping("add")
    public String addAdmin(@Valid Admin admin, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/add";
        }
        adminService.save(admin);
        return "redirect:/admin";
    }

    @GetMapping("/deleteConfirm/{adminId}")
    public String deleteConfirm(@PathVariable Long adminId, Model model) {
        Admin admin = adminService.findById(adminId);
        model.addAttribute("admin", admin);
        return "admin/delete";

    }

    @GetMapping("/delete/{adminId}")
    public String delete(@PathVariable Long adminId) {
        adminService.deleteById(adminId);
        return "redirect:/admin";

    }

    @GetMapping("edit/{adminId}")
    public String editAdmin(@PathVariable Long adminId, Model model) {
        Admin admin = adminService.findById(adminId);
        model.addAttribute("admin", admin);
        return "admin/edit";
    }

    @PostMapping("edit/{adminId}")
    public String editAdmin(@Valid Admin admin, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/add";
        }
        adminService.save(admin);
        return "redirect:/admin";
    }

}
