package pl.coderslab.finalproject.models.festivalEvents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Party extends FestivalEvent {

//    private boolean alcoholAllowed;
//    private boolean liveMusic;

    @Length(min = 3, message = "Nazwa zespołu musi zawierać co najmniej 3 znaki.")
    private String bandName;

}
