package pl.coderslab.finalproject.models.person;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.models.event.Event;
import pl.coderslab.finalproject.models.gift.Gift;
import pl.coderslab.finalproject.models.pass.Pass;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
    private String role;
    private String partnerName;
    private String level;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registrationDate;
    private BigDecimal amountToPay;
    private boolean alreadyPaid;
    private boolean giftsGiven;
    private boolean braceletGiven;
    @ManyToMany
    private List<Gift> gifts;
    @ManyToMany
    private List<Pass> passes;
    private String comments;

    public List<Event> getEvents(){
        List<Event> events = new ArrayList<>();
        for(Pass pass : passes){
            for (Event event: pass.getEvents()) {
                if (!events.contains(event)){
                    events.add(event);
                }
            }
        }
        return events;
    }


}
