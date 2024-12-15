package main.java.rintalatuukka.contacts.util;

import java.io.Console;
import java.util.Arrays;

/**
 * This class is used to get inputs from the user and direct the program to run
 * the appropriate methods elsewhere.
 *
 * @author Tuukka Rintala
 */
public class GetInputs {
    private static final Console C = System.console();
    public static String getValidCommand(String[] COMMANDS) {
        boolean validInput = false;
        String input = "";
        System.out.println("Give a command. h for help.");
        while (!validInput) {
            input = C.readLine().trim();
            if (Arrays.binarySearch(COMMANDS, input) < -1) {
                validInput = true;
            } else {
                System.out.println("Command not recognised.");
            }
        }
        return input;
    }
    public static String getInput() {
        return C.readLine().trim();
    }
    public static boolean yesOrNo(String question) {
        boolean validInput = false;
        String input = "";
        while (!validInput) {
            System.out.println(question);
            input = getInput();
            if (input.equalsIgnoreCase("y")) {
                return true;
            } else if (input.equalsIgnoreCase("n")) {
                return false;
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
}