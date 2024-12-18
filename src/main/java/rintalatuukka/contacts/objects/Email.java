package main.java.rintalatuukka.contacts.objects;

import main.java.rintalatuukka.contacts.objects.Info;
import main.java.rintalatuukka.contacts.util.GetInputs;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * This object contains the info for a finnish ID and methods to manipulate that
 * data.
 */

public class Email implements Info {
    private String info = "";
    public Email() {
        super();
    }
    public Email(String startInfo) {
        setInfo(startInfo);
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String newInfo) {
        if (validate(newInfo)) {
            info = newInfo;
        } else {
            System.out.println("Invalid email. Correct format is for example:" +
                               "mikko.Pajula@gmail.com");
        }
    }
    public boolean validate(String info) {
        // This regex is from https://regex101.com/library/SOgUIV.
        final String regex = "^((?!\\.)[\\w\\-_.]*[^.])(@\\w+)" +
                             "(\\.\\w+(\\.\\w+)?[^.\\W])$";
        Pattern emailPattern = Pattern.compile(regex);
        Matcher emailMatcher = emailPattern.matcher(info);
        return emailMatcher.matches();
    }
    public void inputInfo() {
        boolean validInput = false;
        boolean inputThis = GetInputs.yesOrNo("Input a email? (y/n)");
        info = "";
        if (inputThis) {
            while (!validInput) {
                System.out.println("Please give the email of the contact:");
                validInput = true;
                try {
                    setInfo(GetInputs.getInput());
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    validInput = false;
                }
            }
        }
    }
    public String toString() {
        return info;
    }
}
