package pl.coderslab.finalproject.models.festivalEvents;

import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.models.person.Participant;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public abstract class FestivalEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private Festival festival;

    @ManyToMany
    private List<Participant> participants;

    //    private Date startDate;
//    private Date endDate;
// data powinna zawierać również godzinę!



}
