package pl.coderslab.finalproject.models.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.finalproject.models.festival.Festival;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime start;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime end;
    private String address;
    private String description;
    private String alcohol;
    private boolean warmDishes;
    private String dancefloorLubricity;
    private boolean photoBooth;
    private boolean liveMusic;
    private boolean taster;
    private boolean merch;
    private boolean market;
    private String competition;
    private String duration;
    private String teachers;
    private String level;
    private String style;
    private String soloOrInPairs;
    @ManyToOne
    private Festival festival;

}
