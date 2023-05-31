package pl.coderslab.finalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.finalproject.models.festival.Festival;

public interface FestivalRepository extends JpaRepository<Festival, Long> {

    @Query(
            value = "select * from festival order by adding_date desc limit 1",
            nativeQuery = true)
    Festival findRecentlyAddedFestival();
}
