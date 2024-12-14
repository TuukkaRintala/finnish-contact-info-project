import main.java.rintalatuukka.contacts.util.TextFile;
import main.java.rintalatuukka.contacts.util.GetInputs;
import main.java.rintalatuukka.contacts.objects.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
/**
 * This class starts the program and its purpose is to maintain a text file
 * containing finnish contact information.
 *
 * @author Tuukka Rintala
 */
public class ContactsApp {
    // fileName is a constant String containing the path of the text file to be
    // maintained.
    private static String fileName = "contacts.csv";
    // IOERRORMSG is a constant String containing a message to be displayed in
    // case of a caught IOException.
    private static final String IOERRORMSG = "Error with the file name.";
    private static final String[] COMMANDS = {"h", "e", "q", "o", "u", "a"};
    private static List<Info[]> contactList = new List<Info[]>();
    private static boolean quit = false;
    /**
     * This method is called when the program is run.
     *
     * @param args is an array of Strings containing arguments given when
     * running this app.
     */
    public static void main(final String[] args) {
        openNewFile();
        String input = "";
        while (!quit) {
            input = GetInputs.getValidCommand();
            parseInput(input);
        }
    }
    private static void openNewFile() {
        boolean successful = true;
        File contacts = new File(fileName);
        try {
            successful = contacts.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        contactList = TextFile.openContacts(contacts);
    }
    private static void parseInput(String input) {
        if (input.equalsIgnoreCase(COMMANDS[0])) {
            help();
        } else if (input.equalsIgnoreCase(COMMANDS[1]) || 
                   input.equalsIgnoreCase(COMMANDS[2]) ) {
            quit = true;
        } else if (input.equalsIgnoreCase(COMMANDS[3])) {
            // TODO move asking for path to GetInputs?
            System.out.println("Give the path of the file you want to open:");
            try {
                String path = GetInputs.getInput();
                boolean exists = TextFile.validPath(path);
                if (!exists) {
                    // TODO ask if the user wants to create a new file
                    fileName = path;
                    openNewFile();
                } else {
                    fileName = path;
                    contactList = TextFile.openContacts(new File(path));
                }
            } catch(NullPointerException e) {
                System.out.println("You need to input a path.");
            }
        }
    }
    private static void help() {
        System.out.println("Commands:");
        System.out.println("h    | Prints out each command and what they do.");
        System.out.println("q, e | Quits/exits the program, aka stops it.");
        System.out.println("o    | Opens a new or existing file. Queries you " +
                           "for the file path of that file.");
        System.out.println("u    | Updates info for a contact you specify.");
        System.out.println("a    | Adds a new contact to the list.");
   }
}
