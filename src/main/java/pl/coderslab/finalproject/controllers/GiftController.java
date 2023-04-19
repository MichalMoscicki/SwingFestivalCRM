package pl.coderslab.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.models.gift.Gift;
import pl.coderslab.finalproject.repositories.GiftRepository;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/gift")
public class GiftController {

    private GiftRepository giftRepository;

    public GiftController(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }

    @GetMapping("/add")
    public String addGift(Model model) {
        model.addAttribute("gift", new Gift());
        return "gift/add";
    }

    @PostMapping("/add")
    public String addGift(@Valid Gift gift, BindingResult res) {
        if (res.hasErrors()) {
            return "gift/add";
        }
        giftRepository.save(gift);
        return "redirect:/main";
    }

    @GetMapping("/edit/{id}")
    public String editGiftDetails(Model model, @PathVariable Long id) {
        Optional<Gift> optionalGift = giftRepository.findById(id);
        optionalGift.ifPresent(gift -> model.addAttribute("gift", gift));
        return "gift/edit";
    }

    @PostMapping("/edit/{id}")
    public String editFestivalDetails(@Valid Gift gift, BindingResult res) {
        if (res.hasErrors()) {
            return "main";
        }
        giftRepository.save(gift);
        return "redirect:/main";

    }

    @GetMapping("/deleteConfirm/{id}")
    public String deleteFestivalConfirmation(@PathVariable Long id, Model model) {
        Optional<Gift> giftOptional = giftRepository.findById(id);
        giftOptional.ifPresent(gift -> model.addAttribute("gift", gift));

        return "/gift/delete";

    }

    @GetMapping("/delete/{id}")
    public String deleteFestival(@PathVariable Long id) {
        giftRepository.deleteById(id);
        return "redirect:/main";
    }
}


