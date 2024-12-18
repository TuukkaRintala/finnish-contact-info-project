package main.java.rintalatuukka.contacts.util;

import main.java.rintalatuukka.contacts.objects.*;
import java.util.List;
import java.util.ArrayList;
/**
 * This is a class that contains methods related to displaying the contents of
 * the contact info list.
 *
 * It contains methods that allow the information contained in the given list to
 * be displayed in a uniform manner.
 *
 * @author Tuukka Rintala
 */
public class Display {
    // HEADER contains Strings corresponding to the types of contact info we
    // wish to display.
    private static final String[] HEADER = {"ID", "First name", "Last name",
                                            "Phone number", "Address", "Email"};
    // listLength will contain the amount of characters in the length of the
    // contact list.
    private static int listLength = 1;
    // contactMax is an array of ints containing the lengths of the longest
    // Strings of Info in the contact list.
    private static int[] contactMax = {HEADER[0].length(), HEADER[1].length(),
                                       HEADER[2].length(), HEADER[3].length(),
                                       HEADER[4].length(), HEADER[5].length()};
    /**
     * This method prints out the contents of a contact list.
     *
     * First the longest contact of each type is recorded as well as the amount
     * of characters in the length of the contact list. Then a header is printed
     * showing the categories of info, after which the info is printed.
     *
     * @param contactList this argument contains the list of arrays of Info
     * objects used to find contact information.
     */
    public static void displayContacts(final List<Info[]> contactList) {
        findMaxContactList(contactList);
        listLength = (contactList.size() + "").length();
        printHeader();
        printContactList(contactList);
    }
    /**
     * This method finds the longest info in the contact list.
     *
     * Look at each array contained in the argument and check to see if the info
     * contained is longer than recorded in contactMax, if so, record it into
     * contactMax.
     *
     * @param contactList this argument contains the list of arrays of Info
     * objects used to find contact information.
     */
    public static void findMaxContactList(final List<Info[]> contactList) {
        for (int i = 0; i < contactList.size(); i++) {
            findMaxContact(contactList.get(i));
        }
    }
    /**
     * This method checks to see if the info in the given array are longer than
     * what is recorded.
     *
     * Each Info object in the argument is checked to see how long it is, if it
     * is longer than what is recorded in contactMax, record it.
     *
     * @param contact is an array of Info objects whose length we check.
     */
    public static void findMaxContact(final Info[] contact) {
        for (int i = 0; i < contact.length; i++) {
            int infoLength = contact[i].toString().length();
            if (infoLength > contactMax[i]) {
                contactMax[i] = infoLength;
            }
        }
    }
    /**
     * This method prints all the info from each contact in each list.
     *
     * For each contact, their index is printed, then the information from each
     * Information object contained in the argument is printed.
     *
     * @param contactList this argument contains the list of arrays of Info
     * objects used to find contact information.
     */
    public static void printContactList(final List<Info[]> contactList) {
        for (int i = 0; i < contactList.size(); i++) {
            System.out.print(i + 1);
            for (int j = 0; j <= listLength - ((i + 1) + "").length(); j++) {
                System.out.print(" ");
            }
            System.out.print("|");
            printContact(contactList.get(i));
        }
    }
    /**
     * This method prints all the information from an array of Info objects.
     *
     * Each Info object in the array is printed, then an amount of spaces are
     * printed to equalise with the rest of the same type of Info, and each Info
     * is followed by a |.
     *
     * @param contact is an array of Info objects whose info we will print.
     */
    public static void printContact(final Info[] contact) {
        for (int i = 0; i < contact.length; i++) {
            if (i != contact.length -1) {
                int spacesToMatch = contactMax[i] -
                                                 contact[i].toString().length();
                System.out.print(contact[i]);
                for (int j = 0; j < spacesToMatch; j++) {
                    System.out.print(" ");
                }
                System.out.print("|");
            } else {
                System.out.println(contact[i]);
            }
        }
    }
    /**
     * This method prints a header that identifies the type of information under
     * it.
     *
     * First, spaces are printed to equalise with the index. Then each item in
     * the HEADER array is printed with a preceding | and following spaces to
     * equalise with the rest of the Info.
     */
    public static void printHeader() {
        for (int i = 0; i <= listLength; i++) {
            System.out.print(" ");
        }
        for (int i = 0; i < HEADER.length; i++) {
            System.out.print("|");
            int spacesToMatch = contactMax[i] - HEADER[i].length();
            System.out.print(HEADER[i]);
            for (int j = 0; j < spacesToMatch; j++) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}