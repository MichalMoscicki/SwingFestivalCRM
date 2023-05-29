package pl.coderslab.finalproject.service;

import org.springframework.stereotype.Service;
import pl.coderslab.finalproject.models.event.Event;
import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.models.pass.Pass;
import pl.coderslab.finalproject.models.person.Participant;
import pl.coderslab.finalproject.repositories.EventRepository;
import pl.coderslab.finalproject.repositories.FestivalRepository;
import pl.coderslab.finalproject.repositories.ParticipantRepository;
import pl.coderslab.finalproject.repositories.PassRepository;

import java.util.List;

@Service
public class FestivalService {

    private final ParticipantRepository participantRepository;
    private final PassRepository passRepository;
    private final EventRepository eventRepository;
    private final FestivalRepository festivalRepository;

    public FestivalService(ParticipantRepository participantRepository, PassRepository passRepository, EventRepository eventRepository, FestivalRepository festivalRepository) {
        this.participantRepository = participantRepository;
        this.passRepository = passRepository;
        this.eventRepository = eventRepository;
        this.festivalRepository = festivalRepository;
    }

    public void deleteFestival(Festival festival){

        List<Participant> participants = participantRepository.findAllByFestival(festival);
        participantRepository.deleteAll(participants);

        List<Pass> passes = passRepository.findAllByFestival(festival);
        passRepository.deleteAll(passes);

        List<Event> events = eventRepository.findAllByFestivalOrderByStart(festival);
        eventRepository.deleteAll(events);

        festivalRepository.delete(festival);

    }
}
