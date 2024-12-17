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
 * This class contains methods that interpret the data from a CSV-file into a
 * list of Info-object arrays and allows updating specific parts of that list,
 * adding new information to that list and allows the overwriting of the data in
 * that CSV-file with the data stored in the list.
 *
 * @author Tuukka Rintala
 */

public class TextFile {
    // SEPARATOR is the character used to separate values in the CSV-file.
    private static final char SEPARATOR = ';';
    /**
     * This method takes the stored data in the argument, and parses it into a
     * list of Info-object arrays.
     *
     * Firstly the data in CSV-file is stored in a String list. Then that data
     * is parsed into a list of Info-object arrays.
     *
     * @param contacts is a File that determines where the contact data is.
     * @return a list of Info-object arrays containing the data from the file.
     */
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
    /**
     * This method parses the String list read from a CSV-file into Info-objects
     * stored into arrays nested in a list.
     *
     * Each String in the list is checked and we attempt to make them into an
     * array of Info-objects. Then we add them to a list.
     *
     * @param contactList this argument contains contact information in a list
     * of Strings.
     * @return the information parsed into a list of Info-object arrays.
     */
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
    /**
     * This method parses the argument into an array of Info-objects.
     *
     * The argument is split by the SEPARATOR character, then each part of the
     * String is attempted to be made into an Info object.
     *
     * @param start a String containing contact information separated by a char.
     * @return an array of Info objects containing contact information.
     */
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
    /**
     * This method updates the information of a contact.
     *
     * 
     *
     * @param contacts the File describing the
     * @param contactList a List containing contact information.
     * @return a list of Info-object arrays containing the updated contact
     * information.
     */
    public static List<Info[]> updateFile(final File contacts, 
                                          final List<Info[]> contactList) {
        boolean validInput = false;
        int index = 0;
        if (contactList.size() != 0 ){
            System.out.println("Give the index you wish to update.");
            while(!validInput) {
                validInput = true;
                try {
                    index = Integer.parseInt(GetInputs.getInput());
                    if (index < 1 || index > contactList.size()) {
                        validInput = false;
                        System.out.println("Index out of range.");
                    }
                } catch (IllegalArgumentException e) {
                    validInput = false;
                    System.out.println("Please input an integer.");
                }
            }
            Info[] updateThis = contactList.get(index - 1);
            for(int i = 0; i < updateThis.length; i++) {
                updateThis[i].inputInfo();
            }
            saveIntoFile(contacts, contactList);
        } else {
            System.out.println("No contacts to update.");
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
        saveIntoFile(contacts, contactList);
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
        if (GetInputs.yesOrNo("Do you want to overwrite saved data? (y/n)")) {
            try {
                Files.delete(contacts.toPath());
                boolean successful = contacts.createNewFile();
                BufferedWriter contactsWriter =
                                   new BufferedWriter(new FileWriter(contacts));
                for (int i = 0; i < contactList.size(); i++) {
                    saveContact(contactList.get(i), contactsWriter);
                    contactsWriter.newLine();
                }
                contactsWriter.close();
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
