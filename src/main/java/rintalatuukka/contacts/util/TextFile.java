package main.java.rintalatuukka.contacts.util;

import main.java.rintalatuukka.contacts.objects.*;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ArrayList;

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
        List<String> commaSeparated = new ArrayList<>();
        try {
            commaSeparated = 
                Files.readAllLines(contacts.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        List<Info[]> contactList = parseContactList(commaSeparated);
        return contactList;
    }
    private static List<Info[]> parseContactList(final List<String> contactList) {
        List<Info[]> parsedList = new ArrayList<>();
        int missingLines = 0; // TODO, after reading contacts, give the user a
        // chance to stop the program before updating the csv.
        for (int i = 0; i < contactList.size(); i++) {
            try {
                parsedList.add(stringToContact(contactList.get(i)));
            } catch (IllegalArgumentException e) {
                System.out.println("Line " + (i + 1) + e.getMessage());
                missingLines++;
            }
        }
        return parsedList;
    }
    private static Info[] stringToContact(final String start) {
        final int contactInfoFields = 6;
        final String csvRegex = ("[" + SEPARATOR + "]");
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
        return parsedContact;
    }
    public static List<Info[]> updateFile(final File contacts, 
                              final List<Info[]> contactList, final int index) {
        Info[] updateThis = contactList.get(index - 1);
        for(int i = 0; i < updateThis.length; i++) {
            updateThis[i].inputInfo();
        }
        return contactList;
    }
    public static List<Info[]> appendFile(final File contacts, 
                                          final List<Info[]> contactList) {
        Info[] addThis = {new Id(), new FirstName(), new LastName(),
                              new PhoneNumber(), new Address(), new Email()};
        for (int i = 0; i < addThis.length; i++) {
            try {
                addThis[i].inputInfo();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        contactList.add(addThis);
        return contactList;
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
        if (GetInputs.yesOrNo("Do you want to overwrite saved data?")) {
            try {
                Files.delete(contacts.toPath());
                boolean successful = contacts.createNewFile();
                BufferedWriter contactsWriter =
                                   new BufferedWriter(new FileWriter(contacts));
                for (int i = 0; i < contactList.size(); i++) {
                    saveContact(contactList.get(i), contactsWriter);
                    contactsWriter.newLine();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private static void saveContact(Info[] contact, 
                                    BufferedWriter contactsWriter) {
        try {
            for (int i = 0; i < contact.length; i++) {
                if (i != contact.length - 1) {
                    contactsWriter.write(contact[i].getInfo() + SEPARATOR);
                } else {
                    contactsWriter.write(contact[i].getInfo());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
