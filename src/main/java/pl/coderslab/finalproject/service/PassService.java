package pl.coderslab.finalproject.service;

import org.springframework.stereotype.Service;
import pl.coderslab.finalproject.models.event.Event;
import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.models.pass.Pass;
import pl.coderslab.finalproject.models.person.Participant;
import pl.coderslab.finalproject.repositories.ParticipantRepository;
import pl.coderslab.finalproject.repositories.PassRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PassService {

    private final PassRepository passRepository;
    private final ParticipantRepository participantRepository;

    public PassService(PassRepository passRepository, ParticipantRepository participantRepository) {
        this.passRepository = passRepository;
        this.participantRepository = participantRepository;
    }

    public Pass findById(Long id){
        return passRepository.findById(id).get();
    }

    public void add(Pass pass){
        passRepository.save(pass);
    }

    public void update(Pass pass){
        passRepository.save(pass);
    }

    @Transactional
    public void delete(Long id){
        Pass pass = findById(id);
        List<Event> passEvents = pass.getEvents();
        passEvents.clear();
        passRepository.save(pass);

        List<Participant> participantsWithThisEvent = participantRepository.findAllByPasses(pass);
        for(Participant p : participantsWithThisEvent){
           List<Pass> passes =  p.getPasses();
           passes.remove(pass);
           p.setAmountToPay(p.calculateAmountToPay());
           participantRepository.save(p);
        }
        passRepository.delete(pass);
    }

    public List<Pass> findAllByFestival(Festival festival) {
        return passRepository.findAllByFestival(festival);
    }

    public List<Pass> findAll() {
        return passRepository.findAll();
    }
}
