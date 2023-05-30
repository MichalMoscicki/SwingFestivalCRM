package pl.coderslab.finalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.finalproject.models.merch.Merch;

public interface MerchRepository extends JpaRepository<Merch, Long> {
}
