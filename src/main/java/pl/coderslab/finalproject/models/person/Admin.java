package pl.coderslab.finalproject.models.person;

import pl.coderslab.finalproject.models.festival.Festival;

import javax.persistence.*;
import java.util.List;

@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastNam;
    private String email;
    private String password;
    @ManyToOne
    private SuperAdmin superAdmin;
    @ManyToMany
    private List<Festival> festivals;




}
