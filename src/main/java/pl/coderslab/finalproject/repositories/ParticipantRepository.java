package pl.coderslab.finalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.models.event.Event;
import pl.coderslab.finalproject.models.person.Participant;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    List<Participant> findAllByFestival(Festival festival);
    Participant findByEmailIgnoreCase(String email);
    List<Participant> findAllByLastNameIgnoreCase(String lastName);


//    @Query("select distinct p from Participant p join FETCH p.events where ?1 member of p.events ")
//    List<Participant> findAllByEvent(Event event);
}


