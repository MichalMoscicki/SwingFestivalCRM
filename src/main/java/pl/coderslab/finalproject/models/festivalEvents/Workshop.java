package pl.coderslab.finalproject.models.festivalEvents;

import pl.coderslab.finalproject.models.person.Participant;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Workshop extends FestivalEvent {
    private boolean pairRequired;
    private List<Participant> leaders;
    private List<Participant> followers;
}
