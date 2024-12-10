package main.java.rintalatuukka.contacts.util;

import main.java.rintalatuukka.contacts.objects.Contact;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.charset.Charset;
import java.util.List;

public class TextFile {
    public static boolean OpenContacts(final File contacts) {
        boolean successful = true;
        try {
            List<String> commaSeparated = 
                Files.readAllLines(contacts.toPath(), Charset.defaultCharset());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    private static List<Contact> parseContactList(final List<String> contactList,
                                                  final char separator) {
        List<Contact> parsedList = new List<Contact>();
        int missingLines = 0; // TODO, after reading contacts, give the user a
        // chance to stop the program before updating the csv.
        for (int i = 0; i < contactList.size(); i++) {
            try {
                parsedList.add(stringToContact(contactList.get(i), separator));
            } catch (IllegalArgumentException e) {
                System.out.println("Line " + (i + 1) + e.getMessage());
                missingLines++;
            }
        }
    }
    private static Contact stringToContact(final String start, final char separator) {
        final int contactInfoFields = 6;
        final int optionalFields = 2;
        final String csvRegex = ("[" + separator + "]");
        final String nameRegex = ("[.]")
        String[] separated = start.split(csvRegex, contactInfoFields);
        if (separated.length < (contactInfoFields - optionalFields)) {
            throw new IllegalArgumentException(
                            "contains too few arguments, contents discarded");
        } else {
            Contact parsedContact = new Contact();
            switch (separated.length) {
                case 4: try {
                        parsedContact = new Contact(separated[0],
                        separated[1].split(nameRegex), separated.[2],
                        separated[3]);
                } catch (IllegalArgumentException e) {
                    throw e;
                }
                case 5: try {
                        parsedContact = new Contact(separated[0],
                        separated[1].split(nameRegex), separated.[2],
                        separated[3], separated[4]);
                } catch (IllegalArgumentException e) {
                    throw e;
                }
                default: try {
                        parsedContact = new Contact(separated[0],
                        separated[1].split(nameRegex), separated.[2],
                        separated[3], separated[4], separated[5]);
                } catch (IllegalArgumentException e) {
                    throw e;
                }
            }
        }
    }
}
