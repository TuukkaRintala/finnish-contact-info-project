package main.java.rintalatuukka.contacts.util;

import main.java.rintalatuukka.contacts.objects.Info;
import main.java.rintalatuukka.contacts.objects.Id;
import main.java.rintalatuukka.contacts.objects.FirstName;
import main.java.rintalatuukka.contacts.objects.LastName;
import main.java.rintalatuukka.contacts.objects.PhoneNumber;
import main.java.rintalatuukka.contacts.objects.Address;
import main.java.rintalatuukka.contacts.objects.Email;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.nio.file.Files;
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
    /**
     * SEPARATOR is the character used to separate values in the CSV-file.
     */
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
    private static List<Info[]> parseContactList(
                                               final List<String> contactList) {
        List<Info[]> parsedList = new ArrayList<>();
        int missingLines = 0; // TODO, after reading contacts, give the user a
        // chance to stop the program before updating the csv.
        for (int i = 0; i < contactList.size(); i++) {
            try {
                Info[] addThis = stringToContact(contactList.get(i));
                parsedList.add(addThis);
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
        final int id = 0;
        final int firstName = 1;
        final int lastName = 2;
        final int phoneNumber = 3;
        final int address = 4;
        final int email = 5;
        final int contactInfoFields = 6;
        final String csvRegex = ("[" + SEPARATOR + "]");
        Info[] parsedContact = new Info[contactInfoFields];
        String[] separated = start.split(csvRegex, contactInfoFields);
        if (separated.length != contactInfoFields) {
            throw new IllegalArgumentException(
                  "contains too few arguments, contents discarded");
        } else {
            try {
                parsedContact[id] = new Id(separated[id]);
                parsedContact[firstName] = new FirstName(separated[firstName]);
                parsedContact[lastName] = new LastName(separated[lastName]);
                parsedContact[phoneNumber] =
                                        new PhoneNumber(separated[phoneNumber]);
                parsedContact[address] = new Address(separated[address]);
                parsedContact[email] = new Email(separated[email]);
            } catch (IllegalArgumentException e) {
                throw e;
            }
        }
        return parsedContact;
    }
    /**
     * This method updates the information of a contact.
     *
     * The user is asked to input the index they wish to update, then the
     * information at that index is displayed and the user is asked if they want
     * to update that information. If yes then each Info-object at the specified
     * index in the list is asked to run a method gets new information from the
     * user. Then the list is overwritten onto the CSV. If there are no contacts
     * in the list an error message is printed instead.
     *
     * @param contacts the File storing the contact information.
     * @param contactList a List containing contact information.
     * @return a list of Info-object arrays containing the updated contact
     * information.
     */
    public static List<Info[]> updateFile(final File contacts,
                                          final List<Info[]> contactList) {
        boolean validInput = false;
        int index = 0;
        if (contactList.size() != 0) {
            System.out.println("Give the index you wish to update.");
            // Getting a valid index
            while (!validInput) {
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
            // Determining which contact to update and getting new information
            // from the user
            Info[] updateThis = contactList.get(index - 1);
            List<Info[]> displayList = new ArrayList<>();
            displayList.add(updateThis);
            Display.displayContacts(displayList);
            boolean update = GetInputs.yesOrNo("Update this contact? (y/n)");
            if (update) {
                for (int i = 0; i < updateThis.length; i++) {
                    updateThis[i].inputInfo();
                }
                saveIntoFile(contacts, contactList);
            }
        } else {
            System.out.println("No contacts to update.");
        }
        return contactList;
    }
    /**
     * This method adds an array of contact information to the list.
     *
     * A new array of Info-objects is created, and each object in the array is
     * used to store new information from the user. It is then added to the list
     * and overwritten onto the CSV file.
     *
     * @param contacts the File storing the contact information.
     * @param contactList a List containing contact information.
     * @return a list of Info-object arrays with the added information array.
     */
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
    /**
     * This method asks the user for an index and deletes a contact from that
     * index from the contact list.
     *
     * The user is prompted to give an index that is then validated to make sure
     * it is within range. Then the chosen contact information is displayed and
     * the user is asked if they wish to delete it. If yes, it is removed from
     * the list and the list is overwritten onto the text file.
     *
     * @param contacts is the CSV file where we store the contacts.
     * @param contactList is a list of Info-object arrays containing finnish
     * contact information.
     * @return the second argument with up to one object removed.
     */
    public static List<Info[]> deleteContact(final File contacts,
                                     final List<Info[]> contactList) {
        boolean validInput = false;
        int index = 0;
        if (contactList.size() != 0) {
            System.out.println("Give the index you wish to delete.");
            // Getting a valid index
            while (!validInput) {
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
            // Determining which contact to delete and getting new information
            // from the user
            Info[] deleteThis = contactList.get(index - 1);
            List<Info[]> displayList = new ArrayList<>();
            displayList.add(deleteThis);
            Display.displayContacts(displayList);
            boolean delete = GetInputs.yesOrNo("Delete this contact? (y/n)");
            if (delete) {
                deleteThis = contactList.remove(index - 1);
                saveIntoFile(contacts, contactList);
            }
        } else {
            System.out.println("No contacts to update.");
        }
        return contactList;
    }
    /**
     * This method checks if the given filepath can be read and written to.
     *
     * The path is checked to see if it can be read and then if it can be
     * written to, if either is not possible the program returns false,
     * otherwise it returns true.
     *
     * @param path a String containing the filepath we wish to examine.
     * @return a boolean telling the program whether the path is read/writeable.
     */
    public static boolean validPath(final String path) {
        File checkThis = new File(path);
        if (!checkThis.canRead()) {
            return false;
        } else if (!checkThis.canWrite()) {
            return false;
        }
        return true;
    }

    /**
     * This method overwrites the data from the list of contact information onto
     * the CSV file.
     *
     * The method first asks the user if they wish to overwrite the data, then
     * deletes the old data. Afterwards each contact in the list is written onto
     * the CSV file on their own line, the information from the Info-objects
     * separated by the SEPARATOR character.
     *
     * @param contacts the File storing the contact information.
     * @param contactList a List containing contact information.
     */
    public static void saveIntoFile(final File contacts,
                                    final List<Info[]> contactList) {
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
    /**
     * This method writes the information from a single Info-object into a CSV-
     * file.
     *
     * Each Info-object in the array is queried for their information and that
     * information is written onto the given CSV file and separated using the
     * SEPARATOR character.
     *
     * @param contact the array of Info objects whose info we want to write onto
     * the CSV file.
     * @param contactsWriter a BufferedWriter that is open in the CSV file we
     * want to write to.
     */
    private static void saveContact(final Info[] contact,
                                    final BufferedWriter contactsWriter) {
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
