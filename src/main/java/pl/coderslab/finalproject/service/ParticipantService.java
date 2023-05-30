package pl.coderslab.finalproject.service;

import org.springframework.stereotype.Service;
import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.models.person.Participant;
import pl.coderslab.finalproject.repositories.ParticipantRepository;

import java.util.List;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public void add(Participant participant){
        participantRepository.save(participant);
    }

    public void update(Participant participant){
        participantRepository.save(participant);
    }

    public Participant findById(Long id){
        return participantRepository.findById(id).get();
    }

    public void delete(Long id){
        participantRepository.delete(findById(id));
    }

    public List<Participant> findAllByFestival(Festival festival) {
       return participantRepository.findAllByFestival(festival);
    }

    public Participant findByEmailIgnoreCase(String email) {
        return participantRepository.findByEmailIgnoreCase(email);
    }

    public List<Participant> findAllByLastNameIgnoreCase(String lastName) {
        return participantRepository.findAllByLastNameIgnoreCase(lastName);
    }
}
