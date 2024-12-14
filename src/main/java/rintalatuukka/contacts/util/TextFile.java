package main.java.rintalatuukka.contacts.util;

import main.java.rintalatuukka.contacts.objects.*;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.charset.Charset;
import java.util.List;

/**
 * This class deals with manipulating the contents of the CSV-file that stores
 * contact info.
 *
 * @author Tuukka Rintala
 */

public class TextFile {
    private static final char SEPARATOR = ';';
    public static List<Info[]> openContacts(final File contacts) {
        boolean successful = true;
        try {
            // initialise outside the try-catch
            List<String> commaSeparated = 
                Files.readAllLines(contacts.toPath(), Charset.defaultCharset());
        } catch (IOException e) {
            throw e;
        }
        List<Info[]> contactList = parseContactList(commaSeparated);
        return contactList;
    }
    private static List<Info[]> parseContactList(final List<String> contactList) {
        List<Info[]> parsedList = new List<Info[]>();
        int missingLines = 0; // TODO, after reading contacts, give the user a
        // chance to stop the program before updating the csv.
        for (int i = 0; i < contactList.size(); i++) {
            try {
                parsedList.add(stringToContact(contactList.get(i), SEPARATOR));
            } catch (IllegalArgumentException e) {
                System.out.println("Line " + (i + 1) + e.getMessage());
                missingLines++;
            }
        }
    }
    private static Info[] stringToContact(final String start) {
        final int contactInfoFields = 6;
        final String csvRegex = ("[" + SEPARATOR + "]");
        final String subRegex = ("[.]");
        Info[] parsedContact = new Info[contactInfoFields];
        String[] separated = start.split(csvRegex, contactInfoFields);
        if (separated.length != contactInfoFields) {
            throw new IllegalArgumentException(
                  "contains too few arguments, contents discarded");
        } else {
            try {
                parsedContact[0] = new Id(separated[0]);
                parsedContact[1] = new FirstName(separated[1]);
                parsedContact[2] = new LastName(separated[2]);
                parsedContact[3] = new PhoneNumber(separated[3]);
                parsedContact[4] = new Address(separated[4]);
                parsedContact[5] = new Email(separated[5]);
            } catch (IllegalArgumentException e) {
                throw e; // fucks up the last two being optional unless you 
                // don't throw errors from them?
            }
        }
    }
    public static List<Info[]> updateFile(File contacts, List<Info[]> contactList) {

    }
    public static List<Info[]> appendFile(File contacts, List<Info[]> contactList) {
        
    }
    public static boolean validPath(String path) {
        File checkThis = new File(path);
        if (!checkThis.canRead()) {
            return false;
        } else if (!checkThis.canWrite()) {
            return false;
        }
        return true;
    }
    public static void saveIntoFile(File contacts, List<Info[]> contactList) {

    }
}
