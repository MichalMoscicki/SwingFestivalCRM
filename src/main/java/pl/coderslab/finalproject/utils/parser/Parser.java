package pl.coderslab.finalproject.utils.parser;

import org.springframework.stereotype.Component;
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
import java.util.List;
import java.util.Scanner;


@Component
public class Parser {

    PassRepository passRepository;
    GiftRepository giftRepository;

    public Parser(PassRepository passRepository, GiftRepository giftRepository) {
        this.passRepository = passRepository;
        this.giftRepository = giftRepository;
    }


    public List<Participant> parseFile(File file, Festival festival) {
        List<Participant> list = new ArrayList<>();
        try {
            List<String[]> stringList = readFile(file);
            list = createParticipantsUsingBuilder(stringList, festival);
            return list;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    private List<String[]> readFile(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        List<String[]> rawList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(";");
            rawList.add(line);
        }
        return rawList;
    }

    private List<Participant> createParticipantsUsingBuilder(List<String[]> lineList, Festival festival) {
        List<Participant> participantList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for (String[] line : lineList) {
            new Participant();
            Participant participant = Participant.builder().registrationDate(LocalDateTime.parse(line[0], formatter)).
                    email(line[1]).city(line[2]).firstName(line[3]).lastName(line[4]).phone(line[5])
                    .passes(participantPasses(line[6], festival))
                    .role(line[7]).partnerName(line[8]).role(findRole(line[9], line[10])).gifts(findGifts(line[11], line[12], line[13]))
                    .build();
            if (line.length == 16) {
                participant.setComments(line[15]);
            }
            participant.setAmountToPay(calculateAmountToPay(participant));
            participantList.add(participant);
        }
        return participantList;
    }


    private List<Pass> participantPasses(String passNames, Festival festival) {
        List<Pass> participantPasses = new ArrayList<>();
        List<Pass> allPasses = passRepository.findAllByFestival(festival);
        for (Pass pass : allPasses) {
            if (pass.getName().toLowerCase().trim().equals(passNames.toLowerCase().trim())) {
                participantPasses.add(pass);
            }
        }
        return participantPasses;
    }

    private String findRole(String firstField, String secondField) {
        if (firstField.isEmpty()) {
            return secondField;
        } else {
            return firstField;
        }
    }

    private List<Gift> findGifts(String firstField, String secondField, String thirdField) {

        List<Gift> participantGifts = new ArrayList<>();
        if (firstField.equals("I want a t-shirt/ Chcę koszulkę")) {
            List<Gift> gifts = giftRepository.findAll();
            String sex;
            if (secondField.toLowerCase().contains("male")) {
                sex = "male";
            } else {
                sex = "female";
            }
            String size = thirdField;
            String lookedGiftName = String.format("%s %s", sex, size);
            for (Gift gift : gifts) {
                String giftName = gift.getName().toLowerCase().replace(" ", "");
                if (giftName.equals(lookedGiftName.toLowerCase().replace(" ", ""))) {
                    participantGifts.add(gift);
                }
            }
        }
        return participantGifts;
    }

    private BigDecimal calculateAmountToPay(Participant participant) {

        BigDecimal price = new BigDecimal(0.00);
        for (Pass pass : participant.getPasses()) {
            price = price.add(pass.getPrice());
        }
        if (!participant.getGifts().isEmpty()) {
            for (Gift gift : participant.getGifts()) {
                price = price.add(gift.getPrice());
            }
        }
        return price;
    }

}


