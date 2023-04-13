package pl.coderslab.finalproject.models.festival;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import pl.coderslab.finalproject.models.festivalEvents.FestivalEvent;
import pl.coderslab.finalproject.models.person.Admin;
import pl.coderslab.finalproject.models.person.Participant;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
    @Length(min = 5, message = "Nazwa musi zawierać co najmniej 5 znaków.")
    private String name;


    @FutureOrPresent(message = "Data początkowa musi być teraźniejsza lub w przyszłości")
    private Date startDate;

    @Future(message = "Data końcowa musi być w przyszłości")
    private Date endDate;

    //startDate musi być w przyszłości
    //endDate musi być w przyszłości i musi być po startDate


}
