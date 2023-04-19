package pl.coderslab.finalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.finalproject.models.festivalEvents.FestivalEvent;

public interface EventRepository extends JpaRepository<FestivalEvent, Long> {
}
