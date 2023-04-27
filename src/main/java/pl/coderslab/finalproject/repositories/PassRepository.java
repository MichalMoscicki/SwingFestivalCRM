package pl.coderslab.finalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.models.pass.Pass;

import java.util.List;

public interface PassRepository extends JpaRepository<Pass, Long> {


    List<Pass> findAllByFestival(Festival festival);
}
