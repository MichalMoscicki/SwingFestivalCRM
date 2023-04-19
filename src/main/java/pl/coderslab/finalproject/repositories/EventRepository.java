package pl.coderslab.finalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.finalproject.models.festivalEvents.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
