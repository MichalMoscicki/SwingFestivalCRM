package pl.coderslab.finalproject.models.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.models.festivalEvents.FestivalEvent;

import javax.persistence.*;
import java.math.BigDecimal;
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

    private String firstName;
    private String lastName;
    private String email;
    @ManyToOne
    private Festival festival;

    @ManyToMany(mappedBy = "participants")
    private List<FestivalEvent> festivalEvents;


//    private String phone;
//    private String city;
//    //leader/follower
//    private String role;
//    private String partnerName;
//    private String level;
//    private boolean tShirt;
//    private String tShirtSize;
//    private boolean alreadyPaid;
//    private BigDecimal amountToPay;


}
