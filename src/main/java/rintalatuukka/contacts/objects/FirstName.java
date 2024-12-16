package main.java.rintalatuukka.contacts.objects;

import main.java.rintalatuukka.contacts.objects.Info;
import main.java.rintalatuukka.contacts.util.GetInputs;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * This object contains the info for a finnish ID and methods to manipulate that
 * data.
 */

public class FirstName implements Info {
    private String info = "";
    public FirstName() {
        super();
    }
    public FirstName(String startInfo) {
        setInfo(startInfo);
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String newInfo) {
        if (validate(newInfo)) {
            info = newInfo;
        } else {
            throw new IllegalArgumentException("Invalid first name.");
        }
    }
    public boolean validate(String info) {
        // The inclusion of nordic letters in the regex is from stackoverflow,
        // slightly modified: https://bit.ly/3ZJnEG9
        final String regex = "^[([A-Z]|Å|Ä|Ö)][([a-z]|å|ä|ö)+]{2,50}";
        Pattern namePattern = Pattern.compile(regex);
        Matcher nameMatcher = namePattern.matcher(info);
        return nameMatcher.matches();
    }
    public void inputInfo() {
        boolean validInput = false;
        while (!validInput) {
            System.out.println("Please give the id of the contact:");
            validInput = true;
            try {
                setInfo(GetInputs.getInput());
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
