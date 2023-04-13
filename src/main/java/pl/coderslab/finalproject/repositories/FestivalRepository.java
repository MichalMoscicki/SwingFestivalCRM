package pl.coderslab.finalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.finalproject.models.festival.Festival;

public interface FestivalRepository extends JpaRepository<Festival, Long> {

}
