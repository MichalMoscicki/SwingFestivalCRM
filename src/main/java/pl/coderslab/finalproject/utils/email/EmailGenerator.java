package pl.coderslab.finalproject.utils.email;

import org.springframework.stereotype.Component;
import pl.coderslab.finalproject.models.event.Event;
import pl.coderslab.finalproject.models.gift.Gift;
import pl.coderslab.finalproject.models.pass.Pass;
import pl.coderslab.finalproject.models.person.Participant;

import java.util.List;

@Component
public class EmailGenerator {

    public String generateInfoEmail(Participant participant) {
        StringBuilder headerBuilder = new StringBuilder();
        for (Pass pass : participant.getPasses()) {
            headerBuilder.append(String.format("-%s\n", pass.getName()));
        }
        for (Gift gift : participant.getGifts()) {
            headerBuilder.append(String.format("- Tshirt: %s\n", gift.getName()));
        }
        String passesAndGifts = headerBuilder.toString();


        String messageHeader = String.format("Witaj %s!\n" +
                        "\n" +
                        "Cieszymy się, że będziesz z nami na %s. \n" +
                        "Wykupiłeś:\n" +
                        "%s\n" +
                        "Razem do zapłaty masz: %s. DANE DO PRZELEWU.\n" +
                        "\n" +
                        "Poniżej znajdziesz wszystkie informacje o wydarzeniach, na które się zapisałeś:",
                participant.getFirstName(),
                participant.getFestival().getName(),
                passesAndGifts,
                participant.getAmountToPay());

        StringBuilder bodyBuilder = new StringBuilder();
        List<Event> participantEvents = participant.getEvents();
        for (Event event : participantEvents) {
            bodyBuilder.append(generateEventInfo(event)).append("\n");
        }
        String messageBody = bodyBuilder.toString();

        String messageFooter = "To jest wiadomość wygenerowana automatycznie. Jeżeli coś się nie zgadza lub masz pytania, skontaktuj się z nami DANE KONTAKTOWE.\n" +
                "Pozdrawiamy serdecznie!\u2028Jednorożce";

        String message = String.format("%s\n%s\n%s", messageHeader, messageBody, messageFooter);
        System.out.println(message);
        System.out.println();
        return message;
    }

    private String generateEventInfo(Event event) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("Wydarzenie: %s\n", event.getName()));
        stringBuilder.append(String.format("Adres: %s\n", event.getAddress()));
        stringBuilder.append(String.format("Początek: %s\n", event.getStart()));
        stringBuilder.append(String.format("Koniec: %s\n", event.getEnd()));
        stringBuilder.append(String.format("Adres: %s\n", event.getAddress()));
        stringBuilder.append(String.format("Opis: %s\n", event.getDescription()));

        if (event.getType().equals("party")) {
            stringBuilder.append(String.format("Parkiet: %s\n", event.getDancefloorLubricity()));
            stringBuilder.append(String.format("Konkursy: %s\n", event.getCompetition()));
            stringBuilder.append(String.format("Alkohol: %s\n", event.getAlcohol()));
            if (event.isTaster()) {
                stringBuilder.append("Taster: tak\n");
            } else {
                stringBuilder.append("Taster: nie\n");
            }
            if (event.isLiveMusic()) {
                stringBuilder.append("Muzyka na żywo: tak\n");
            } else {
                stringBuilder.append("Muzyka na żywo: nie\n");
            }
            if (event.isPhotoBooth()) {
                stringBuilder.append("Fotobudka: tak\n");
            } else {
                stringBuilder.append("Fotobudka: nie\n");
            }
            if (event.isMarket()) {
                stringBuilder.append("Wystawcy: tak\n");
            } else {
                stringBuilder.append("Wystawcy: nie\n");
            }
            if (event.isMerch()) {
                stringBuilder.append("Dostępny festivalowy merch: tak\n");
            } else {
                stringBuilder.append("Dostępny festivalowy merch: nie\n");
            }
        }
        if (event.getType().equals("workshop")) {
            stringBuilder.append(String.format("%s %s %s\n", event.getStyle(), event.getSoloOrInPairs(), event.getLevel()));
            stringBuilder.append(String.format("Prowadzący: %s\n", event.getTeachers()));
            stringBuilder.append(String.format("Czas trwania: %s\n", event.getDuration()));
        }

        return stringBuilder.toString();
    }


}


