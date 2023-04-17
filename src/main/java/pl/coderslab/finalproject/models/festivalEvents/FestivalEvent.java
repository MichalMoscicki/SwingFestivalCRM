package pl.coderslab.finalproject.models.festivalEvents;

import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.models.person.Participant;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class FestivalEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private BigDecimal price;
    private String address;
    private String description;
    @ManyToOne
    private Festival festival;
    @ManyToMany
    private List<Participant> participants;

}
