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
        for (int i = 0; i < contactList.size(); i++) {
            parsedList.add(tringToContact(contactList.get(i), separator));
        }
    }
    private static Contact stringToContact(final String start, final char separator) {
        final int contactInfoFields = 5;
        String[] separated = 
    }
}
