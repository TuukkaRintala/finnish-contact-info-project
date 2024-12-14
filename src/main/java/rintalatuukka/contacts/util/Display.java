package main.java.rintalatuukka.contacts.util;

import main.java.rintalatuukka.contacts.objects.*;
import java.util.List;
/**
 * This is a class that contains methods related to displaying the contents of
 * the contact info list.
 *
 * @author Tuukka Rintala
 */
public class Display {
    private final String[] HEADER = {"ID", "First name", "Last name",
                                     "Phone number", "Address", "Email"};
    public static void displayContacts(final List<Info[]> contactList) {
        int[] contactMax = {HEADER[0].length(), HEADER[1].length(),
                            HEADER[2].length(), HEADER[3].length(),
                            HEADER[4].length(), HEADER[5].length()};
        contactMax = findMaxContactList(contactList, contactMax);
    }
    public static int[] findMaxContactList(final List<Info[]> contactList,
                                           int[] contactMax) {
        for (int i = 0; i < contactList.size(); i++) {
            contactMax = findMaxContact(contactList.get(i), contactMax);
        }
        return contactMax;
    }
    public static int[] findMaxContact(final Info[] contact, int[] contactMax) {
        for (int = 0; i < contact.length; i++) {
            int infoLength = contact[i].length();
            if (infoLength > contactMax[i]) {
                contactMax[i] = infoLength;
            }
        }
        return contactMax;
    }
    public static void printContactList(final List<Info[]> contactList,
                                        final int[] contactMax) {
        for (int i = 0; i < contactList.size(); i++) {
            printContact(contactList.get(i), contactMax);
        }
    }
    public static void printContact(final Info[] contact, final int[] contactMax) {
        for (int i = 0; i < contact.length; i++) {
            if (i != contact.length -1) {
                int spacesToMatch = contact[i].length() - contactMax[i];
                System.out.print(contact[i] + (spacesToMatch * " ") + "|");
            } else {
                System.out.println(contact[i]);
            }
        }
    }
    public static void printHeader(final int[] contactMax) {
        for (int i = 0; i < HEADER.length; i++) {
            if (i != HEADER.length - 1) {
                int spacesToMatch = HEADER[i].length() - contactMax[i];
                System.out.print(HEADER[i] + (spacesToMatch * " ") + "|");
            } else {
                System.out.println(HEADER[i]);
            }
        }
    }
}