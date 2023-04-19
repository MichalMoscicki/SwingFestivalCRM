package pl.coderslab.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.repositories.FestivalRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/festival")
public class FestivalController {

    FestivalRepository festivalRepository;

    public FestivalController(FestivalRepository festivalRepository) {
        this.festivalRepository = festivalRepository;
    }

    @GetMapping("/add")
    public String addFestival(Model model) {
        model.addAttribute("festival", new Festival());
        return "festival/add";
    }

    @PostMapping("/add")
    public String addFestival(@Valid Festival festival, BindingResult res) {
        if (res.hasErrors()) {
            return "festival/add";
        }
        festivalRepository.save(festival);
        return "redirect:/festival";
    }

    @GetMapping("/details/{id}")
    public String festivalDetails(@PathVariable Long id, Model model) {
        Optional<Festival> optionalFestival = festivalRepository.findById(id);
        optionalFestival.ifPresent(festival -> model.addAttribute("festival", festival));

        return "festival/details";
    }

    @GetMapping("/edit/{id}")
    public String editFestivalDetails(Model model, @PathVariable Long id) {
        Optional<Festival> optionalFestival = festivalRepository.findById(id);
        optionalFestival.ifPresent(festival -> model.addAttribute("festival", festival));
        return "festival/edit";
    }

    @PostMapping("/edit/{id}")
    public String editFestivalDetails(@Valid Festival festival, BindingResult res) {
        if (res.hasErrors()) {
            return "festival/edit";
        }
        festivalRepository.save(festival);
        return "redirect:/festival";
    }


    @GetMapping("/deleteConfirm/{id}")
    public String deleteFestivalConfirmation(@PathVariable Long id, Model model) {
        Optional<Festival> festivalOptional = festivalRepository.findById(id);
        festivalOptional.ifPresent(festival -> model.addAttribute("festival", festival));

        return "/festival/delete";
    }

    @GetMapping("/delete/{id}")
    public String deleteFestival(@PathVariable Long id) {
        festivalRepository.deleteById(id);
        return "redirect:/festival";
    }

}
