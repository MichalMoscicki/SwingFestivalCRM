package pl.coderslab.finalproject.models.festivalEvents;

import javax.persistence.Entity;

@Entity
public class Party extends FestivalEvent {

    private boolean alcoholAllowed;
    private boolean liveMusic;
    private String bandName;

}
