package pl.coderslab.finalproject.service;

import org.springframework.stereotype.Service;
import pl.coderslab.finalproject.models.event.Event;
import pl.coderslab.finalproject.models.pass.Pass;
import pl.coderslab.finalproject.repositories.EventRepository;
import pl.coderslab.finalproject.repositories.PassRepository;

import java.util.List;

@Service
public class EventService {

    private final PassRepository passRepository;
    private final EventRepository eventRepository;

    public EventService(PassRepository passRepository, EventRepository eventRepository) {
        this.passRepository = passRepository;
        this.eventRepository = eventRepository;
    }

    //powinien przyjomwać ID
    public void delete(Event event){

        List<Pass> passesWithThisEvent = passRepository.findAllByEvents(event);


        for(Pass pass: passesWithThisEvent){
            System.out.println(pass.getName());
            //usuń z listy passów
            //update pass
        }

        // co z participantami? - spr, czy działa

        //pobieramy passy, w których znajduje się event
        // usuwamy z listy eventów
        //usuwamy event

        //eventRepository.delete(event);
    }

}
