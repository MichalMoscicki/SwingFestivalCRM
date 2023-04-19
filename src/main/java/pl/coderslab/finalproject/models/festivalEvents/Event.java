package pl.coderslab.finalproject.models.festivalEvents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.models.person.Participant;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {
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
