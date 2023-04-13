package pl.coderslab.finalproject.models.festivalEvents;

import pl.coderslab.finalproject.models.person.Participant;

import javax.persistence.*;
import java.util.List;

@Entity
public abstract class FestivalEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;

    @ManyToMany
    private List<Participant> participants;

    //pozostałe informacje
}
