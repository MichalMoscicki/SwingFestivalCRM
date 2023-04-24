package pl.coderslab.finalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.finalproject.models.pass.Pass;

public interface PassRepository extends JpaRepository<Pass, Long> {


}
