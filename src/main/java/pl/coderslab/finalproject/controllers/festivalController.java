package pl.coderslab.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.repositories.FestivalRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/festival")
public class festivalController {

    FestivalRepository festivalRepository;

    public festivalController(FestivalRepository festivalRepository) {
        this.festivalRepository = festivalRepository;
    }

    @GetMapping("")
    public String displayAllFestivals(Model model){
        List<Festival> festivals = festivalRepository.findAll();
        model.addAttribute("festivals", festivals);
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
        return "redirect:/festival";
    }


    @GetMapping("/details/{id}")
    public String festivalDetails(@PathVariable Long id, Model model){
        Optional<Festival> optionalFestival = festivalRepository.findById(id);
        optionalFestival.ifPresent(festival -> model.addAttribute("festival", festival));

        return "festival/details";
    }

    @GetMapping("/edit")
    public String editFestivalDetails(){
        return "festival/edit";
    }



    @GetMapping("/deleteConfirm/{id}")
    public String deleteFestivalConfirmation(@PathVariable Long id, Model model){
        Optional<Festival> festivalOptional = festivalRepository.findById(id);
        festivalOptional.ifPresent(festival -> model.addAttribute("festival", festival));
       //tu zrób jakiś sprytny else?

        return "/festival/delete";
    }

    @GetMapping("/delete/{id}")
    public String deleteFestival(@PathVariable Long id){
        festivalRepository.deleteById(id);
        return "redirect:/festival";
    }

}
