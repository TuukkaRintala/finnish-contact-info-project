package main.java.rintalatuukka.contacts.util;

import ContactsApp;
import java.io.Console;
import java.util.Arrays;

/**
 * This class is used to get inputs from the user and direct the program to run
 * the appropriate methods elsewhere.
 *
 * @author Tuukka Rintala
 */
public class GetInputs {
    private final String[] COMMANDS = {"h", "e", "q", "o"};
    private final Console C = System.console();
    public static boolean getValidCommand() {
        boolean validInput = false;
        String input = "";
        System.out.println("Give a command. h for help.");
        while (!validInput) {
            input = C.readLine().trim();
            if (Arrays.binarySearch(COMMANDS, input)) {
                validInput = true;
            } else {
                System.out.println("Command not recognised.")
            }
        }
        if (input.equalsIgnoreCase(COMMANDS[0])) {
            help();
            return true;
        } else if (input.equalsIgnoreCase(COMMANDS[1]) || 
                   input.equalsIgnoreCase(COMMANDS[2]) ) {
            return false;
        } else if (input.equalsIgnoreCase(COMMANDS[3])) {
            System.out.println("Give the path of the file you want to open:");
            try {
                boolean exists = TextFile.validPath(C.readLine().trim());
                if (exists) {
                    // call ContactsApp
                }
            } catch(NullPointerException e) {
                System.out.println("You need to input a path.")
            }
        }
    }
    /*
    public static Contact addNewContact() {
        boolean validInput = false;
        Contact addThis = new Contact();
        while (!validInput) {
            System.out.println("Please give the id of the contact:");
            validInput = true;
            try {
                addThis.setId(c.readLine().trim());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        }
    }
    */
   public static void help() {
        System.out.println();
   }
}