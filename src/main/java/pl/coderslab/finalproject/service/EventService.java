package pl.coderslab.finalproject.service;

import org.springframework.stereotype.Service;
import pl.coderslab.finalproject.models.event.Event;
import pl.coderslab.finalproject.models.pass.Pass;
import pl.coderslab.finalproject.repositories.EventRepository;
import pl.coderslab.finalproject.repositories.PassRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EventService {

    private final PassRepository passRepository;
    private final EventRepository eventRepository;

    public EventService(PassRepository passRepository, EventRepository eventRepository) {
        this.passRepository = passRepository;
        this.eventRepository = eventRepository;
    }

    public Event findEvent(Long id) {
        return eventRepository.findById(id).get();
    }

    @Transactional
    public void delete(Long id) {
        Event event = findEvent(id);
        List<Pass> passesWithThisEvent = passRepository.findAllByEvents(event);
        for (Pass pass : passesWithThisEvent) {
            pass.getEvents().remove(event);
            passRepository.save(pass);
        }
        eventRepository.delete(event);
    }

    public void addEvent(Event event) {
        eventRepository.save(event);
    }

    public void update(Event event) {
        eventRepository.save(event);
    }


}
