import main.java.rintalatuukka.contacts.util.Display;
import main.java.rintalatuukka.contacts.util.GetInputs;
import main.java.rintalatuukka.contacts.util.TextFile;
import main.java.rintalatuukka.contacts.objects.Info;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
/**
 * This class starts the program and its purpose is to maintain a text file
 * containing finnish contact information.
 *
 * @author Tuukka Rintala
 */
public class ContactsApp {
    /**
     * fileName is a constant String containing the path of the text file to be
     * maintained.
     */
    private static String fileName = "contacts.csv";
    /**
     * COMMANDS is an array containing the commands a user can input.
     */
    private static final String[] COMMANDS = {"h", "e", "q", "o", "u", "a",
                                              "d"};
    /**
     * contactList is the list of all contacts the user wants to manipulate.
     */
    private static List<Info[]> contactList = new ArrayList<>();
    /**
     * quit is a boolean informing the program whether it should quit operation.
     */
    private static boolean quit = false;
    /**
     * This method is called when the program is run.
     *
     * It opens a file using the openNewFile method, then asks for a command
     * corresponding to the ones specified in COMMANDS and parses them using
     * the method parseInput. This is repeated until the user inputs a quit
     * command.
     *
     * @param args is an array of Strings containing arguments given when
     * running this app.
     */
    public static void main(final String[] args) {
        openNewFile();
        String input = "";
        while (!quit) {
            String prompt = "Give a command. h for help.";
            String error = "Command not recognised.";
            input = GetInputs.getValidCommand(COMMANDS, prompt, error);
            parseInput(input);
        }
    }
    /**
     * This method opens a text file, reads and displays the contacts in that
     * file
     *
     * A new file will be created if one doesn't exist, then the contacts will
     * be stored in contactList and overwritten onto that text file if the user
     * wishes.
     */
    private static void openNewFile() {
        boolean successful = true;
        File contacts = new File(fileName);
        try {
            successful = contacts.createNewFile();
            contactList = TextFile.openContacts(contacts);
            TextFile.saveIntoFile(contacts, contactList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Display.displayContacts(contactList);
    }
    /**
     * This method calls different segments of the program depending on the
     * command it was given.
     *
     * The argument is compared against the COMMANDS array and executes help,
     * quit/exit program, open a new file, update a contact or add a new
     * contact.
     *
     * @param input is a String containing the validated command to be parsed.
     */
    private static void parseInput(final String input) {
        final int help = 0;
        final int exit = 1;
        final int quitIndex = 2;
        final int open = 3;
        final int update = 4;
        final int add = 5;
        final int delete = 6;
        File contacts = new File(fileName);
        if (input.equalsIgnoreCase(COMMANDS[help])) {
            help();
        } else if (input.equalsIgnoreCase(COMMANDS[exit])
                   || input.equalsIgnoreCase(COMMANDS[quitIndex])) {
            boolean validInput = false;
            while (!validInput) {
                System.out.println("Are you sure you want to quit? (y/n)");
                String response = GetInputs.getInput();
                if (response.equalsIgnoreCase("y")) {
                    TextFile.saveIntoFile(contacts, contactList);
                    quit = true;
                    validInput = true;
                } else if (response.equalsIgnoreCase("n")) {
                    validInput = true;
                }
            }
        } else if (input.equalsIgnoreCase(COMMANDS[open])) {
            TextFile.saveIntoFile(contacts, contactList);
            System.out.println("Give the path of the file you want to open:");
            try {
                String path = GetInputs.getInput();
                boolean exists = TextFile.validPath(path);
                if (!exists) {
                    fileName = path;
                    openNewFile();
                } else {
                    fileName = path;
                    contactList = TextFile.openContacts(new File(path));
                }
                Display.displayContacts(contactList);
            } catch (NullPointerException e) {
                System.out.println("You need to input a path.");
            }
        } else if (input.equalsIgnoreCase(COMMANDS[update])) {
            TextFile.updateFile(contacts, contactList);
            Display.displayContacts(contactList);
        } else if (input.equalsIgnoreCase(COMMANDS[add])) {
            TextFile.appendFile(contacts, contactList);
            Display.displayContacts(contactList);
        } else if (input.equalsIgnoreCase(COMMANDS[delete])) {
            TextFile.deleteContact(contacts, contactList);
            Display.displayContacts(contactList);
        }
    }
    /**
     * This method prints a list of commands this program can execute and
     * explanations for each of them.
     */
    private static void help() {
        System.out.println("Commands:");
        System.out.println("h    | Prints out each command and what they do.");
        System.out.println("q, e | Quits/exits the program, aka stops it.");
        System.out.println("o    | Opens a new or existing file. Queries you "
                           + "for the file path of that file.");
        System.out.println("u    | Updates info for a contact you specify.");
        System.out.println("a    | Adds a new contact to the list.");
        System.out.println("d    | Deletes an existing contact from the list.");
    }
}
