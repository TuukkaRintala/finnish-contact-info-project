package main.java.rintalatuukka.contacts.objects;

import main.java.rintalatuukka.contacts.objects.Info;
import main.java.rintalatuukka.contacts.util.GetInputs;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * This object contains the info for a finnish ID and methods to manipulate that
 * data.
 */

public class Id implements Info {
    private String info = "";
    public Id(String startInfo) {
        setInfo(startInfo);
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String newInfo) {
        if (validate(newInfo)) {
            info = newInfo;
        } else {
            throw new IllegalArgumentException("Invalid ID.");
        }
    }
    public boolean validate(String info) {
        // This regex is from https://regex101.com/library/cIohyA, and has been
        // edited to include A-F and U-W as the century sign.
        final String regex = "^(0[1-9]|[1-2][0-9]|3[0-1])(0[1-9]|1[0-2])[0-9]" +
        "{2}[[A-F][U-Y]+-][0-9]{3}[A-z0-9]$";
        Pattern idPattern = Pattern.compile(regex);
        Matcher idMatcher = idPattern.matcher(info);
        return idMatcher.matches();
    }
    public void inputInfo() {
        boolean validInput = false;
        Contact addThis = new Contact();
        while (!validInput) {
            System.out.println("Please give the id of the contact:");
            validInput = true;
            try {
                addThis.setId(GetInputs.getInput());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        }
    }
    public String toString() {
        return info;
    }
}
