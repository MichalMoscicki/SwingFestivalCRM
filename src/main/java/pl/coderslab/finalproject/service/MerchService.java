package pl.coderslab.finalproject.service;

import org.springframework.stereotype.Service;
import pl.coderslab.finalproject.models.merch.Merch;
import pl.coderslab.finalproject.repositories.MerchRepository;

@Service
public class MerchService {

    private final MerchRepository merchRepository;

    public MerchService(MerchRepository merchRepository) {
        this.merchRepository = merchRepository;
    }

    public Merch findById(Long id){
        return merchRepository.findById(id).get();
    }

    public void add(Merch merch){
        merchRepository.save(merch);
    }

    public void update(Merch merch){
        merchRepository.save(merch);
    }

    public void delete(Long id){
        Merch merch = findById(id);
        //wywalić go z wszystkich użytkowników, którzy mają dany merch!
        merchRepository.delete(merch);
    }
}
