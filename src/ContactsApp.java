package;

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
    // FILENAME is a constant String containing the path of the text file to be
    // maintained.
    private static final String FILENAME = "contacts.csv";
    // IOERRORMSG is a constant String containing a message to be displayed in
    // case of a caught IOException.
    private static final String IOERRORMSG = "Error with the file name.";
    /**
     * This method is called when the program is run.
     *
     * @param args is an array of Strings containing arguments given when
     * running this app.
     */
    public static void main(final String[] args) {
        boolean successful = true;
        File contacts = new File(FILENAME);
        try {
            successful = contacts.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        successful = TextFile.openContacts(contacts);
        boolean quit = false;
        while (!quit) {
            GetInputs.getValidCommand();
        }
    }
}
