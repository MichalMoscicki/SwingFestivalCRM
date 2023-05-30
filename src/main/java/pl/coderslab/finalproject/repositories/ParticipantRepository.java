package pl.coderslab.finalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.models.event.Event;
import pl.coderslab.finalproject.models.merch.Merch;
import pl.coderslab.finalproject.models.pass.Pass;
import pl.coderslab.finalproject.models.person.Participant;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    List<Participant> findAllByFestival(Festival festival);
    Participant findByEmailIgnoreCase(String email);
    List<Participant> findAllByLastNameIgnoreCase(String lastName);
    List<Participant> findAllByPasses(Pass pass);

    List<Participant> findAllByMerch(Merch merch);
}


