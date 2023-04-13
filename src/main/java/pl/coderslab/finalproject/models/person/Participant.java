package pl.coderslab.finalproject.models.person;

import pl.coderslab.finalproject.models.festivalEvents.FestivalEvent;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String city;
    //leader/follower
    private String role;
    private String partnerName;
    private String level;
    private boolean tShirt;
    private String tShirtSize;

    private boolean alreadyPaid;
    private BigDecimal amountToPay;

    @ManyToMany
    private List<FestivalEvent> festivalEvents;




}
