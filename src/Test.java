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
/*
import main.java.rintalatuukka.contacts.objects.Id;
import main.java.rintalatuukka.contacts.objects.FirstName;
import main.java.rintalatuukka.contacts.objects.LastName;
import main.java.rintalatuukka.contacts.objects.PhoneNumber;
import main.java.rintalatuukka.contacts.objects.Address;
import main.java.rintalatuukka.contacts.objects.Email;
*/

public class Test {
    public static void main(final String[] args) {
        /*
        String newInfo = "Hämeenkatu 17.33800.Sodankylä";
        String[] splitInfo = newInfo.split("[.]", 3);
        try {
            setStreet(splitInfo[0]);
            setZipCode(splitInfo[1]);
            setCity(splitInfo[2]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Address data formatted wrong. Address data " +
                "should always contain three fields divided by a full stop .");
        }
        */
        System.out.println(new String("..").split("[.]", 3).length);
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
