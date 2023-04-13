package pl.coderslab.finalproject.models.festival;

import pl.coderslab.finalproject.models.festivalEvents.FestivalEvent;
import pl.coderslab.finalproject.models.person.Admin;
import pl.coderslab.finalproject.models.person.Participant;
import pl.coderslab.finalproject.models.person.SuperAdmin;

import javax.persistence.*;
import java.util.List;



@Entity
public class Festival {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany
    private List<FestivalEvent> festivalEvents;

    @OneToMany
    private List<Participant> participants;

    @ManyToMany
    private List<Admin> admins;


}
