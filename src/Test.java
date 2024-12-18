import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.charset.Charset;
import java.util.List;
import java.util.ArrayList;
import main.java.rintalatuukka.contacts.objects.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Test {
    public static void main(final String[] args) {
        final String regex = "^[a-zA-ZåäöÅÄÖ]+\\s[0-9a-zA-Z]+\\s\\d{5}\\s[a-zA-ZåäöÅÄÖ]+$";
        Pattern emailPattern = Pattern.compile(regex);
        String ads = "Hämeenkatu 1 33100";
        Matcher emailMatcher = emailPattern.matcher("Hämeenkatu 1 33100 Tampere");
        System.out.println(ads.matches(regex));
        /*
        File contacts = new File("whatevs.csv");
        try {
            boolean successful = contacts.createNewFile();
            Files.delete(contacts.toPath());
            contacts.createNewFile();
            List<String> commaSeparated = 
                Files.readAllLines(contacts.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        */
        /*
        String csv = "310199-547C;Joni;Nikula;+358402245666;Hämeenkatu 17.33800.Tampere;joni.nikula@gmail.com";
        String[] splitCsv = csv.split("[;]", 6);
        for (int i = 0; i < splitCsv.length; i++) {
            System.out.println(splitCsv[i]);
        }
        */
       /*
        List<Info[]> contacts = new ArrayList<>();
        Info[] contact = {new FirstName("Tuukka"), new LastName("Rintala")};
        contacts.add(contact);
        System.out.println(contacts.get(0)[1]);
        */
    }
}
