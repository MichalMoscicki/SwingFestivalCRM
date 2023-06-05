package pl.coderslab.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.finalproject.models.merch.Merch;
import pl.coderslab.finalproject.repositories.MerchRepository;
import pl.coderslab.finalproject.service.MerchService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/merch")
public class MerchController {

    private final MerchService merchService;
    public MerchController(MerchService merchService) {
        this.merchService = merchService;
    }


    @GetMapping("")
    public String displayAll(Model model){
        List<Merch> merchList = merchService.findAll();
        model.addAttribute("merch", merchList);
        return "merchAll";

    }


    @GetMapping("/add")
    public String addMerch(Model model) {
        model.addAttribute("merch", new Merch());
        return "merch/add";
    }

    @PostMapping("/add")
    public String addMerch(@Valid Merch merch, BindingResult res) {
        if (res.hasErrors()) {
            return "merch/add";
        }
        merchService.add(merch);
        return "redirect:/merch";
    }

    @GetMapping("/edit/{id}")
    public String editMerchDetails(Model model, @PathVariable Long id) {
        Merch merch = merchService.findById(id);
        model.addAttribute("merch", merch);
        return "merch/edit";
    }

    @PostMapping("/edit/{id}")
    public String editMerchDetails(@Valid Merch merch, BindingResult res) {
        if (res.hasErrors()) {
            return "main";
        }
        merchService.add(merch);
        return "redirect:/merch";

    }

    @GetMapping("/deleteConfirm/{id}")
    public String deleteMerchConfirmation(@PathVariable Long id, Model model) {
        Merch merch = merchService.findById(id);
        model.addAttribute("merch", merch);

        return "/merch/delete";

    }

    @GetMapping("/delete/{id}")
    public String deleteMerch(@PathVariable Long id) {
        merchService.delete(id);
        return "redirect:/merch";
    }
}


