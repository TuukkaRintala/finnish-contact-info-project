package main.java.rintalatuukka.contacts.objects;

import main.java.rintalatuukka.contacts.util.GetInputs;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * This object contains the info for a finnish ID and methods to manipulate that
 * data.
 *
 * @author Tuukka Rintala
 * {@inheritDoc}
 */

public class Email implements Info {
    // info contains the email information we wish to store in this object.
    private String info = "";
    /**
     * This constructor is used for creating empty Email objects.
     */
    public Email() {
        super();
    }
    /**
     * This is a constructor which recieves its information in the argument.
     *
     * The information is stored using setInfo, so that it is validated and
     * throws an exception if the data is invalid.
     *
     * @param startInfo a String containing an email.
     */
    public Email(final String startInfo) {
        setInfo(startInfo);
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(final String newInfo) {
        if (validate(newInfo)) {
            info = newInfo;
        } else {
            // Don't throw an exception because this information is optional.
            System.out.println("Invalid email. Correct format is for example:"
                               + "mikko.Pajula@gmail.com");
        }
    }
    /**
     * This method validates if the argument is a correctly formatted email.
     *
     * A regular expression is compiled to check that the first or last
     * character isn't a '.', the first part consists of letters or certain
     * other characters(-_.), then there should be an @ character followed by
     * letters and at least one dot followed by letters. It is then matched
     * against the argument.
     *
     * @param newInfo a String containing the email we wish to validate.
     * @return a boolean denoting whether the argument was valid.
     */
    public boolean validate(final String newInfo) {
        // This regex is from https://regex101.com/library/SOgUIV. Escaped some
        // characters properly for Java.
        final String regex = "^((?!\\.)[([A-ZÅÄÖa-zåäö])\\-_.]*[^.])(@\\w+)"
                             + "(\\.([A-ZÅÄÖa-zåäö])+(\\.([A-ZÅÄÖa-zåäö])+)?"
                             + "[^.\\W])$";
        Pattern emailPattern = Pattern.compile(regex);
        Matcher emailMatcher = emailPattern.matcher(newInfo);
        return emailMatcher.matches();
    }
    public void inputInfo() {
        boolean validInput = false;
        boolean inputThis = GetInputs.yesOrNo("Input an email? (y/n)");
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
