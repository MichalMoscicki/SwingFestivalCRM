package pl.coderslab.finalproject.service.fileUploadService;

import org.springframework.stereotype.Service;
import pl.coderslab.finalproject.models.pass.Pass;
import pl.coderslab.finalproject.repositories.PassRepository;

@Service
public class PassService {

    private final PassRepository passRepository;

    public PassService(PassRepository passRepository) {
        this.passRepository = passRepository;
    }

    public Pass findById(Long id){
        return passRepository.findById(id).get();
    }


}
