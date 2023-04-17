package pl.coderslab.finalproject.models.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.models.festivalEvents.FestivalEvent;
import pl.coderslab.finalproject.models.gift.Gift;

import javax.persistence.*;
import javax.validation.constraints.Email;
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
    private List<FestivalEvent> festivalEvents;
    private BigDecimal amountToPay;
    private boolean alreadyPaid;
    private boolean giftsGiven;
    private boolean braceletGiven;
    @ManyToMany
    private List<Gift> gifts;
    private String comments;


}
