package main.java.rintalatuukka.contacts.util;

import java.io.Console;

/**
 * This class is used to get inputs from the user and return them to other
 * Classes.
 *
 * The purpose of the methods in this class is to streamline other classes
 * sanitise some aspects of the inputs and to avoid having to import Console
 * elsewhere.
 *
 * @author Tuukka Rintala
 */
public class GetInputs {
    /**
     * C is the console used to get inputs by the methods in this class.
     */
    private static final Console C = System.console();
    /**
     * This method is used to get a command specified in the argument.
     *
     * First the user is prompted to give a command, then we take their input
     * from the console and check to see if it is one of the ones given in the
     * argument. If yes, we return that input, if not we print an error message
     * and take then next input and check it again.
     *
     * @param commands an array of Strings containing acceptable commands.
     * @param prompt is a String that is printed to ask the user for a command.
     * @param error is a String that gets printed when the user inputs an
     * invalid command.
     * @return a String containing the validated command.
     */
    public static String getValidCommand(final String[] commands,
                                      final String prompt, final String error) {
        boolean validInput = false;
        String input = "";
        System.out.println(prompt);
        while (!validInput) {
            input = C.readLine().trim();
            for (int i = 0; i < commands.length; i++) {
                if (commands[i].equalsIgnoreCase(input)) {
                    return input;
                }
            }
            System.out.println(error);
        }
        return input;
    }
    /**
     * This method takes a user input and trims extra whitspace from it.
     *
     * @return a trimmed user input String.
     */
    public static String getInput() {
        return C.readLine().trim();
    }
    /**
     * This method asks a yes or no question given in the argument and returns
     * the answer as a boolean.
     *
     * The method prints the argument until the user inputs y or n, to denote
     * their answer (yes or no), then returns that.
     *
     * @param question a String containing a yes or no question.
     * @return a boolean containing the answer to the argument.
     */
    public static boolean yesOrNo(final String question) {
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
        return false;
    }
}
