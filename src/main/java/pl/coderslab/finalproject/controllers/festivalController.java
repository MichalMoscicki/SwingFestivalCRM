package pl.coderslab.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.repositories.FestivalRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/festival")
public class festivalController {

    FestivalRepository festivalRepository;

    public festivalController(FestivalRepository festivalRepository) {
        this.festivalRepository = festivalRepository;
    }

    @GetMapping("")
    public String displayAllFestivals(){
        return "festival/main";
    }

    @GetMapping("/add")
    public String addFestival(Model model){
        model.addAttribute("festival", new Festival());
        return "festival/add";
    }

    @PostMapping("/add")
    public String addFestival(@Valid Festival festival, BindingResult res){
        if(res.hasErrors()){
            return "festival/add";
        }
        festivalRepository.save(festival);
        return "redirect:/festival/details";
    }

    @GetMapping("/details")
    public String festivalDetails(){
        return "festival/details";
    }

    @GetMapping("/edit")
    public String editFestivalDetails(){
        return "festival/edit";
    }

    @GetMapping("/delete")
    public String deleteFestival(){
        return "festival/delete";
    }

}
