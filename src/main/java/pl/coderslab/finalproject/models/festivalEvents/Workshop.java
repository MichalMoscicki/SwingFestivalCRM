package pl.coderslab.finalproject.models.festivalEvents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.finalproject.models.person.Participant;

import javax.persistence.Entity;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Workshop extends FestivalEvent {
    private boolean pairRequired;
//    private List<Participant> leaders;
//    private List<Participant> followers;
}
