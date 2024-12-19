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
public class Id implements Info {
    /**
     * info contains the finnish ID information we wish to store in this object.
     */
    private String info = "";
    /**
     * This constructor is for creating empty Id objects and should be used with
     * care.
     *
     * It should not pass validation in other areas of the program, so
     * information should be added to it as soon as possible, either using
     * setInfo or inputInfo.
     */
    public Id() {
        super();
    }
    /**
     * This is a constructor which recieves its information in the argument.
     *
     * The information is stored using setInfo, so that it is validated and
     * throws an exception if the data is invalid.
     *
     * @param startInfo a String containing a finnish ID number.
     */
    public Id(final String startInfo) {
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
            throw new IllegalArgumentException("Invalid ID.");
        }
    }
    /**
     * This method validates if the argument is a correctly formatted finnish ID
     * number.
     *
     * A regular expression is compiled to check if the first 6 numbers are a
     * valid birthdate, if the character afterwards is a valid century sign, if
     * the next three are a valid individual number and if the last character is
     * a valid control character. It is then matched against the argument.
     *
     * @param newInfo a String containing the finnish ID we wish to validate.
     * @return a boolean denoting whether the argument was valid.
     */
    public boolean validate(final String newInfo) {
        // This regex is from https://regex101.com/library/cIohyA, and has been
        // edited to include A-F and U-W as the century sign.
        final String regex = "^(0[1-9]|[1-2][0-9]|3[0-1])(0[1-9]|1[0-2])[0-9]"
                             + "{2}[[A-F][U-Y]+-][0-9]{3}[A-z0-9]$";
        Pattern idPattern = Pattern.compile(regex);
        Matcher idMatcher = idPattern.matcher(newInfo);
        return idMatcher.matches();
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
