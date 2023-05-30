package pl.coderslab.finalproject.models.person;

import lombok.*;
import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.models.event.Event;
import pl.coderslab.finalproject.models.merch.Merch;
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
@Builder(access = AccessLevel.PUBLIC)
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
    private LocalDateTime registrationDate;
    private BigDecimal amountToPay;
    private boolean alreadyPaid;
    private boolean giftsGiven;
    private boolean braceletGiven;
    @ManyToMany
    private List<Merch> merch;
    @ManyToMany
    private List<Pass> passes;
    private String comments;

    public List<Event> getEvents() {
        List<Event> events = new ArrayList<>();
        for (Pass pass : passes) {
            for (Event event : pass.getEvents()) {
                if (!events.contains(event)) {
                    events.add(event);
                }
            }
        }
        return events;
    }


    public BigDecimal calculateAmountToPay() {
        BigDecimal price = new BigDecimal("0.00");
        if (merch != null) {
            for (Merch m : merch) {
                price = price.add(m.getPrice());
            }
        }
        if (passes != null) {
            for (Pass p : passes) {
                price = price.add(p.getPrice());
            }
        }

        return price;
    }


}
