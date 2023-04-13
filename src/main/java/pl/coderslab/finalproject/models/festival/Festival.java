package pl.coderslab.finalproject.models.festival;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.finalproject.models.festivalEvents.FestivalEvent;
import pl.coderslab.finalproject.models.person.Admin;
import pl.coderslab.finalproject.models.person.Participant;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;



@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Festival {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date startDate;
    private Date endDate;

    //dajemy tu walidacje - festiwal, nie krótsza nazwa niż 3 znaki i nie może być nullem
    //endDate nie może być wcześniejsza niż startDate i obie muszą być w przyszłości


}
