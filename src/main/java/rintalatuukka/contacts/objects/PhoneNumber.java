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

public class PhoneNumber implements Info {
    // info contains the phone number information we wish to store in this
    // object.
    private String info = "";
    /**
     * This constructor is for creating empty PhoneNumber objects and should be
     * used with care.
     *
     * It should not pass validation in other areas of the program, so
     * information should be added to it as soon as possible, either using
     * setInfo or inputInfo.
     */
    public PhoneNumber() {
        super();
    }
    /**
     * This is a constructor which recieves its information in the argument.
     *
     * The information is stored using setInfo, so that it is validated and
     * throws an exception if the data is invalid.
     *
     * @param startInfo a String containing a phone number.
     */
    public PhoneNumber(final String startInfo) {
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
            throw new IllegalArgumentException("Invalid phone number.");
        }
    }
    /**
     * This method validates if the argument is a correctly formatted phone
     * number.
     *
     * A regular expression is compiled to check if there is a country code at
     * the start (it can be in brackets), and if the rest are numbers in a
     * correct amount, also parts of the phone number can be separated using
     * '.',' ' or '-'. It is then matched against the argument.
     *
     * @param newInfo a String containing the phone number we wish to validate.
     * @return a boolean denoting whether the argument was valid.
     */
    public boolean validate(final String newInfo) {
        // This regex is modified from https://ihateregex.io/expr/phone/ to
        // escape the characters properly in Java.
        final String regex = "^[+]?[(]?[0-9]{3}[)]?[-\s\\.]?[0-9]{3}[-\s\\.]?"
                             + "[0-9]{4,6}$";
        Pattern phonePattern = Pattern.compile(regex);
        Matcher phoneMatcher = phonePattern.matcher(newInfo);
        return phoneMatcher.matches();
    }
    public void inputInfo() {
        boolean validInput = false;
        while (!validInput) {
            System.out.println("Please give the phone number of the contact:");
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
