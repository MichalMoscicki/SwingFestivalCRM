package pl.coderslab.finalproject.controllers;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.utils.parser.Parser;
import pl.coderslab.finalproject.models.person.Participant;
import pl.coderslab.finalproject.repositories.FestivalRepository;
import pl.coderslab.finalproject.repositories.ParticipantRepository;
import pl.coderslab.finalproject.service.fileUploadService.FileConverter;
import pl.coderslab.finalproject.service.fileUploadService.StorageFileNotFoundException;
import pl.coderslab.finalproject.service.fileUploadService.StorageService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/addFromFile")
public class AddFromFile {
    private final Parser parser;
    private final FestivalRepository festivalRepository;
    private final ParticipantRepository participantRepository;
    private  final StorageService storageService;
    private final FileConverter fileConverter;

    public AddFromFile(Parser parser, FestivalRepository festivalRepository, ParticipantRepository participantRepository, StorageService storageService, FileConverter fileConverter) {
        this.parser = parser;
        this.festivalRepository = festivalRepository;
        this.participantRepository = participantRepository;
        this.storageService = storageService;
        this.fileConverter = fileConverter;
    }


    @GetMapping("/{festivalId}/chooseFile")
    public String displayChooseForm(@PathVariable Long festivalId, Model model) {
        model.addAttribute("festivalId", festivalId);
        return "addFromFile/chooseFile";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
    @PostMapping("/{festivalId}/chooseFile")
    public String displayChooseForm(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes,
                                   @PathVariable Long festivalId) throws IOException {

        File convertedFile = fileConverter.convertToFile(file);

        Optional<Festival> festivalOptional = festivalRepository.findById(festivalId);
        List<Participant> participantList = parser.parseFile(convertedFile, festivalOptional.get());
        int participantsAdded = 0;

        for (Participant participant : participantList) {
            participantsAdded++;
            participantRepository.save(participant);
        }

        return String.format( "redirect:/addFromFile/%s/result/%s", festivalId, participantsAdded);
    }

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}

    @GetMapping("{festivalId}/result/{participantAdded}")
    public String result(@PathVariable Long festivalId,
                         @PathVariable Long participantAdded,
                         Model model) {
    model.addAttribute("participantsAdded", participantAdded);
    model.addAttribute("festivalId", festivalId);
        return "addFromFile/result";
    }


}
