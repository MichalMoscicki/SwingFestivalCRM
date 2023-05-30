package pl.coderslab.finalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.finalproject.models.event.Event;
import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.models.pass.Pass;

import javax.transaction.Transactional;
import java.util.List;

public interface PassRepository extends JpaRepository<Pass, Long> {


    List<Pass> findAllByFestival(Festival festival);

    List<Pass> findAllByEvents(Event event);

}
