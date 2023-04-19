package pl.coderslab.finalproject.models.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.models.festivalEvents.Event;
import pl.coderslab.finalproject.models.gift.Gift;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Festival festival;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String city;
    private LocalDateTime registrationDate;
    @ManyToMany(mappedBy = "participants")
    private List<Event> festivalEvents;
    private BigDecimal amountToPay;
    private boolean alreadyPaid;
    private boolean giftsGiven;
    private boolean braceletGiven;
    @ManyToMany
    private List<Gift> gifts;
    private String comments;


}
