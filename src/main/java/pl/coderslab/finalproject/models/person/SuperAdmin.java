package pl.coderslab.finalproject.models.person;

import pl.coderslab.finalproject.models.festival.Festival;

import javax.persistence.*;
import java.util.List;

@Entity
public class SuperAdmin {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;



    @OneToMany
    private List<Festival> festivals;

    @OneToMany
    private List<Admin> admins;


}
