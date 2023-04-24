package pl.coderslab.finalproject.models.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.finalproject.models.event.Event;
import pl.coderslab.finalproject.models.festival.Festival;
import pl.coderslab.finalproject.models.gift.Gift;
import pl.coderslab.finalproject.models.pass.Pass;
import pl.coderslab.finalproject.models.person.Participant;
import pl.coderslab.finalproject.repositories.GiftRepository;
import pl.coderslab.finalproject.repositories.PassRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class Parser {

    PassRepository passRepository;
    GiftRepository giftRepository;

    public Parser(PassRepository passRepository, GiftRepository giftRepository) {
        this.passRepository = passRepository;
        this.giftRepository = giftRepository;
    }


    public List<Participant> parseFile(String filePath, Festival festival) {
        List<Participant> list = new ArrayList<>();
        try {
            List<String[]> stringList = readFile(filePath);
            list = createParticipants(stringList, festival);
            return list;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    private List<String[]> readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        List<String[]> rawList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(",");
            rawList.add(line);
        }
        return rawList;
    }

    private List<Participant> createParticipants(List<String[]> lineList, Festival festival) {
        List<Participant> participantList = new ArrayList<>();

        for (String[] line : lineList) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            Participant participant = new Participant();
            participant.setFestival(festival);
            participant.setRegistrationDate(LocalDateTime.parse(line[0], formatter));
            participant.setEmail(line[1]);
            participant.setCity(line[2]);
            participant.setFirstName(line[3]);
            participant.setLastName(line[4]);
            participant.setPhone(line[5]);
            //--- passes---//
            List<Pass> participantPasses = new ArrayList<>();
            List<Pass> allPasses = passRepository.findAll();
            for (Pass pass : allPasses) {
                if (pass.getName().toLowerCase().trim().equals(line[6].toLowerCase().trim())) {
                    participantPasses.add(pass);
                }
            }
            participant.setPasses(participantPasses);

            participant.setRole(line[7]);
            participant.setPartnerName(line[8]);
            //--- role---//
            if (line[10].isEmpty()) {
                participant.setLevel(line[9]);
            } else {
                participant.setLevel(line[10]);
            }
            //--- gifts---//
            List<Gift> participantGifts = new ArrayList<>();
            participant.setGifts(participantGifts);
            if (line[11].equals("I want a t-shirt/ Chcę koszulkę")) {
                List<Gift> gifts = giftRepository.findAll();
                String sex;
                if (line[12].toLowerCase().contains("male")) {
                    sex = "male";
                } else {
                    sex = "female";
                }
                String size = line[13];
                String lookedGiftName = String.format("%s %s", sex, size);
                for (Gift gift : gifts) {
                    String giftName = gift.getName().toLowerCase().replace(" ", "");
                    if (giftName.equals(lookedGiftName.toLowerCase().replace(" ", ""))) {
                        participantGifts.add(gift);
                    }
                }
                participant.setGifts(participantGifts);
            }
            //--- comments---//
            if (line.length == 16) {
                participant.setComments(line[15]);
            }
            //--- price---//
            BigDecimal price = new BigDecimal(0.00);
            for (Pass pass : participant.getPasses()) {
                price = price.add(pass.getPrice());
            }
            if(!participant.getGifts().isEmpty()) {
                for (Gift gift : participant.getGifts()) {
                    price = price.add(gift.getPrice());
                }
            }
            participant.setAmountToPay(price);
            //--- adding to list---//
            participantList.add(participant);
        }
        return participantList;
    }

    public List<String> showLines(String filePath) {
        List<String> stringList = new ArrayList<>();
        try {
            List<String[]> rawList = readFile(filePath);

            stringList = rawList.stream().flatMap(Arrays::stream).collect(Collectors.toList());
            return stringList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return stringList;
    }
}
