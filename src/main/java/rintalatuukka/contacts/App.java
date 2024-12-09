package main.java.rintalatuukka.contacts;

import main.java.rintalatuukka.contacts.objects.*;
import main.java.rintalatuukka.contacts.util.*;
import java.io.File;

public class App {
    public static void run(File contacts) {
        TextFile.OpenContacts(contacts);
    }
}
