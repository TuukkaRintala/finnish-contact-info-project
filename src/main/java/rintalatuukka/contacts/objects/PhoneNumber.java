package main.java.rintalatuukka.contacts.objects;

import main.java.rintalatuukka.contacts.objects.Info;
import main.java.rintalatuukka.contacts.util.GetInputs;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * This object contains the info for a finnish ID and methods to manipulate that
 * data.
 */

public class PhoneNumber implements Info {
    private String info = "";
    public PhoneNumber(String startInfo) {
        setInfo(startInfo);
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String newInfo) {
        if (validate(newInfo)) {
            info = newInfo;
        } else {
            throw new IllegalArgumentException("Invalid phone number.");
        }
    }
    public boolean validate(String info) {
        // This regex is modified from https://ihateregex.io/expr/phone/ to
        // escape the characters properly in Java.
        final String regex = "^[+]?[(]?[0-9]{3}[)]?[-\s\\.]?[0-9]{3}[-\s\\.]?" +
                             "[0-9]{4,6}$";
        Pattern phonePattern = Pattern.compile(regex);
        Matcher phoneMatcher = phonePattern.matcher(info);
        return phoneMatcher.matches();
    }
    public void inputInfo() {
        boolean validInput = false;
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
