package main.java.rintalatuukka.contacts.objects;

import main.java.rintalatuukka.contacts.util.GetInputs;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * This object contains the a last name and methods to manipulate that data.
 *
 * @author Tuukka Rintala
 * {@inheritDoc}
 */

public class LastName implements Info {
    /**
     * info contains the last name information we wish to store in this object.
     */
    private String info = "";
    /**
     * This constructor is for creating empty LastName objects and should be
     * used with care.
     *
     * It should not pass validation in other areas of the program, so
     * information should be added to it as soon as possible, either using
     * setInfo or inputInfo.
     */
    public LastName() {
        super();
    }
    /**
     * This is a constructor which recieves its information in the argument.
     *
     * The information is stored using setInfo, so that it is validated and
     * throws an exception if the data is invalid.
     *
     * @param startInfo a String containing a last name.
     */
    public LastName(final String startInfo) {
        setInfo(startInfo);
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(final String newInfo) {
        if (validate(newInfo)) {
            info = newInfo;
        } else {
            // Throw an exception because this information is not optional.
            throw new IllegalArgumentException("Invalid last name.");
        }
    }
    /**
     * This method validates if the argument is a correctly formatted last name.
     *
     * A regular expression is compiled to check if the character is an
     * uppercase letter (including ÅÄÖ) and that the rest are lower case letters
     * and that the name isn't absurdly long. It is then matched against the
     * argument.
     *
     * @param newInfo a String containing the last name we wish to validate.
     * @return a boolean denoting whether the argument was valid.
     */
    public boolean validate(final String newInfo) {
        // The inclusion of nordic letters in the regex is from stackoverflow,
        // slightly modified: https://bit.ly/3ZJnEG9
        final String regex = "^[([A-Z]|Å|Ä|Ö)][([a-z]|å|ä|ö)+]{1,50}$";
        Pattern namePattern = Pattern.compile(regex);
        Matcher nameMatcher = namePattern.matcher(newInfo);
        return nameMatcher.matches();
    }
    public void inputInfo() {
        boolean validInput = false;
        while (!validInput) {
            System.out.println("Please give the last name of the contact:");
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
