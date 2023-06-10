package pl.coderslab.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.finalproject.models.person.Admin;
import pl.coderslab.finalproject.models.role.Role;
import pl.coderslab.finalproject.service.AdminService;
import pl.coderslab.finalproject.service.RoleService;


import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final RoleService roleService;

    public AdminController(AdminService adminService, RoleService roleService) {
        this.adminService = adminService;
        this.roleService = roleService;
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
        return "adminDeleteConfirm";

    }

    @GetMapping("/delete/{adminId}")
    public String delete(@PathVariable Long adminId) {
        boolean isDeleteSuccessful = adminService.deleteById(adminId);
        if(!isDeleteSuccessful){
            return "redirect:/admin/cannotDeleteLastAdmin";
        }
        return "redirect:/admin";
    }

    @GetMapping("edit/{adminId}")
    public String editAdmin(@PathVariable Long adminId, Model model) {
        Admin admin = adminService.findById(adminId);
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        model.addAttribute("admin", admin);

        return "adminEdit";
    }

    @PostMapping("edit/{adminId}")
    public String editAdmin(@Valid Admin admin, BindingResult result, @PathVariable Long adminId) {
        if (result.hasErrors()) {
            return "adminEdit";
        }
        adminService.update(admin, adminId);
        return "redirect:/admin";
    }


    @GetMapping("/cannotDeleteLastAdmin")
    public String cannotDeleteLastAdmin(){
        return "/adminLast";
    }


    @GetMapping("/changePassword/{adminId}")
    public String changePassword(Model model, @PathVariable Long adminId){
        model.addAttribute(adminService.findById(adminId));
        return "adminChangePassword";
    }


    @PostMapping("/changePassword/{adminId}")
    public String changePassword(@PathVariable Long adminId,
                                 @RequestParam String password1,
                                 @RequestParam String password2){
        if(password1.equals(password2)){
            adminService.changePassword(adminId, password1);
        } else {
            return "adminChangePassword";
        }
        return "redirect:/admin";
    }
}
