package pl.coderslab.finalproject.models.parser;

import pl.coderslab.finalproject.models.event.Event;
import pl.coderslab.finalproject.models.gift.Gift;
import pl.coderslab.finalproject.models.person.Participant;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {

    //rozkminić modyfikatory dostępu
    public List<String[]> readFile(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        List<String[]> rawList = new ArrayList<>();
        while(scanner.hasNextLine()){
            String[] line = scanner.nextLine().split(",");
            rawList.add(line);
        }
        return rawList;
    }

    public List<Participant> createParticipants(List<String[]> lineList){
        List<Participant> participantList = new ArrayList<>();

        for(String[] line : lineList){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            Participant participant = new Participant();
            participant.setRegistrationDate(LocalDateTime.parse(line[0], formatter));
            participant.setEmail(line[1]);
            participant.setCity(line[2]);
            participant.setFirstName(line[3]);
            participant.setLastName(line[4]);
            participant.setPhone(line[5]);

            //----- to do poprawy----//
//            Event event = new Event();
//            event.setName(line[6]);
//            List<Event> events = new ArrayList<>();
//            events.add(event);
//            participant.setEvents(events);
            //----------------------------//

            participant.setRole(line[7]);
            participant.setPartnerName(line[8]);

            if(line[10].isEmpty()){
                participant.setLevel(line[9]);
            } else {
                participant.setLevel(line[10]);
            }

            if(line[11].equals("I want a t-shirt/ Chcę koszulkę")){
                List<Gift> gifts = new ArrayList<>();
                Gift gift = new Gift();
                gift.setName("T-shirt");
                gift.setDescription(String.format("%s  %s",line[12], line[13]));
                gifts.add(gift);
                participant.setGifts(gifts);
            }
            if(line.length == 16) {
                participant.setComments(line[15]);
            }
            participantList.add(participant);
        }
        return participantList;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Parser parser = new Parser();
        File file = new File("/Users/michalmoscicki/Documents/CodersLab/finalProject/src/main/resources/SPL.csv");
        List<Participant> list = parser.createParticipants(parser.readFile(file));
        for(Participant participant : list){
            System.out.println(participant.toString());
            System.out.println();
        }


    }
}
