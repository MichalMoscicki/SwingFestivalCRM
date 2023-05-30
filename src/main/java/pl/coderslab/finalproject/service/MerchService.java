package pl.coderslab.finalproject.service;

import org.springframework.stereotype.Service;
import pl.coderslab.finalproject.models.merch.Merch;
import pl.coderslab.finalproject.models.person.Participant;
import pl.coderslab.finalproject.repositories.MerchRepository;
import pl.coderslab.finalproject.repositories.ParticipantRepository;

import java.util.List;

@Service
public class MerchService {

    private final MerchRepository merchRepository;
    private final ParticipantRepository participantRepository;

    public MerchService(MerchRepository merchRepository,
                        ParticipantRepository participantRepository) {
        this.merchRepository = merchRepository;
        this.participantRepository = participantRepository;
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
        List<Participant> participantsWithThisMerch = participantRepository.findAllByMerch(merch);
        for(Participant p : participantsWithThisMerch){
            p.getMerch().remove(merch);
            p.setAmountToPay(p.calculateAmountToPay());
            participantRepository.save(p);
        }
        merchRepository.delete(merch);
    }

    public List<Merch> findAll() {
        return merchRepository.findAll();
    }
}
