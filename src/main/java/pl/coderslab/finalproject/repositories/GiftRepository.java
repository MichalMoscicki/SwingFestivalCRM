package pl.coderslab.finalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.finalproject.models.gift.Gift;

public interface GiftRepository extends JpaRepository<Gift, Long> {
}
