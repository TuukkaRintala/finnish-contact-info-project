package main.java.rintalatuukka.contacts.objects;

import main.java.rintalatuukka.contacts.util.GetInputs;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * This object contains the info for a finnish addresses and methods to
 * manipulate that data.
 *
 * @author Tuukka Rintala
 * {@inheritDoc}
 */

public class Address implements Info {
    /**
     * info contains the street address, zip code and city information separated
     * by a dot.
     */
    private String info = "..";
    /**
     * street contains the street address as a String.
     */
    private String street = "";
    /**
     * zipCode contains the zip code as a String.
     */
    private String zipCode = "";
    /**
     * city contains the city as a String.
     */
    private String city = "";
    /**
     * This constructor is used for creating empty Address objects.
     */
    public Address() {
        super();
    }
    /**
     * This is a constructor which recieves its information in the argument.
     *
     * The information is stored using setInfo, so that it is validated and
     * throws an exception if the data is invalid.
     *
     * @param startInfo a String containing a finnish address, with the street
     * address, zipcode and city separated by a '.'.
     */
    public Address(final String startInfo) {
        setInfo(startInfo);
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(final String newInfo) {
        String[] splitInfo = newInfo.split("[.]");
        try {
            setStreet(splitInfo[0], true);
            setZipCode(splitInfo[1], true);
            setCity(splitInfo[2], true);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Address data formatted wrong. Address data "
                               + "should always contain three fields divided "
                               + "by a full stop .");
        }
    }
    /**
     * This method updates the information to contain the current street
     * address, zip code and city separated by dots.
     */
    public void updateInfo() {
        info = street + "." + zipCode + "." + city;
    }
    /**
     * This method sets the street address of this object. Also adds a dot to
     * separate from zip code and city.
     *
     * @param newStreet a String containing the new street address.
     * @param constructor a boolean that informs the method if its being run
     * from a constructor.
     */
    public void setStreet(final String newStreet, final boolean constructor) {
        street = "";
        if (validateStreet(newStreet)) {
            street = newStreet;
        } else if (!constructor) {
            System.out.println("Invalid street address. Correct format is for"
                               + " example: Hämeenkatu 12 B4.");
        }
        updateInfo();
    }
    /**
     * This method sets the zip code of this object. Also adds a dot to separate
     *  from the street address and city.
     *
     * @param newZip a String containing the new zip code.
     * @param constructor a boolean that informs the method if its being run
     * from a constructor.
     */
    public void setZipCode(final String newZip, final boolean constructor) {
        zipCode = "";
        if (validateZip(newZip)) {
            zipCode = newZip;
        } else if (!constructor) {
            System.out.println("Invalid zip code. Correct format is for"
                               + " example: 33800.");
        }
        updateInfo();
    }
    /**
     * This method sets the city of this object. Does not add a dot because it
     * is the last value in this object.
     *
     * @param newCity a String containing the new city.
     * @param constructor a boolean that informs the method if its being run
     * from a constructor.
     */
    public void setCity(final String newCity, final boolean constructor) {
        city = "";
        if (validateCity(newCity)) {
            city = newCity;
        } else if (!constructor) {
            // Don't throw an exception because this information is optional.
            System.out.println("Invalid city. Correct format is for example:"
                               + " Tampere.");
        }
        updateInfo();
    }
    /**
     * This method validates if the argument is a correctly formatted street
     * address.
     *
     * A regular expression is compiled to check that the first letter is
     * capitalised, and the rest of that word is not and isn't too long, the
     * next part is the street number and can be up to three numbers long.
     * Lastly there is an option to include an apartment number that includes
     * your staircase. It is then matched against the argument.
     *
     * @param newInfo a String containing the street address we wish to
     * validate.
     * @return a boolean denoting whether the argument was valid.
     */
    public boolean validateStreet(final String newInfo) {
        final String regex = "^[([A-Z]|Å|Ä|Ö)][([a-z]|å|ä|ö)+]{1,50}"
                             + "([ ]([1-9]|[1-9][0-9]|[1-9][0-9][0-9]){1,3})"
                             + "([ ][A-Z]([1-9]|[0][1-9]|[1-9][0-9]){1,2})??";
        Pattern streetPattern = Pattern.compile(regex);
        Matcher streetMatcher = streetPattern.matcher(newInfo);
        return streetMatcher.matches();
    }
    /**
     * This method validates if the argument is a correctly formatted zip code.
     *
     * A regular expression is compiled to check if there are 5 numbers.It is
     * then matched against the argument.
     *
     * @param newInfo a String containing the zip code§ we wish to validate.
     * @return a boolean denoting whether the argument was valid.
     */
    public boolean validateZip(final String newInfo) {
        final String regex = "^[0-9]{5}$";
        Pattern zipPattern = Pattern.compile(regex);
        Matcher zipMatcher = zipPattern.matcher(newInfo);
        return zipMatcher.matches();
    }
    /**
     * This method validates if the argument is a correctly formatted city.
     *
     * A regular expression is compiled to check that the first letter is
     * capitalised and that it isn't too long. It is then matched against the
     * argument.
     *
     * @param newInfo a String containing the last name we wish to validate.
     * @return a boolean denoting whether the argument was valid.
     */
    public boolean validateCity(final String newInfo) {
        final String regex = "^[([A-Z]|Å|Ä|Ö)][([a-z]|å|ä|ö)+]{1,50}$";
        Pattern cityPattern = Pattern.compile(regex);
        Matcher cityMatcher = cityPattern.matcher(newInfo);
        return cityMatcher.matches();
    }
    public void inputInfo() {
        // Clear existing information.
        info = "";
        street = "";
        zipCode = "";
        city = "";
        // Since this information is optional, ask the user if they whether or
        // not they wish to input each piece of info.
        boolean validInput = false;
        boolean inputThis = GetInputs.yesOrNo("Input a street address? (y/n)");
        if (inputThis) {
            while (!validInput) {
                System.out.println("Please give the street address of the"
                                   + " contact:");
                setStreet(GetInputs.getInput(), false);
                if (!street.equals("")) {
                    validInput = true;
                }
            }
        // Adding a dot even if no street address is present, so that import
        // works later.
        } else {
            info += ".";
        }
        inputThis = GetInputs.yesOrNo("Input a zip code? (y/n)");
        validInput = false;
        if (inputThis) {
            while (!validInput) {
                System.out.println("Please give the zip code of the contact:");
                setZipCode(GetInputs.getInput(), false);
                if (!zipCode.equals("")) {
                    validInput = true;
                }
            }
        // Adding a dot even if no zip code is present, so that import works
        // later.
        } else {
            info += ".";
        }
        validInput = false;
        inputThis = GetInputs.yesOrNo("Input a city? (y/n)");
        if (inputThis) {
            while (!validInput) {
                System.out.println("Please give the city of the contact:");
                setCity(GetInputs.getInput(), false);
                if (!city.equals("")) {
                    validInput = true;
                }
            }
        }
    }
    public String toString() {
        String display = "";
        if (!street.equals("")) {
            display += street + " ";
        }
        if (!zipCode.equals("")) {
            display += zipCode + " ";
        }
        display += city;
        return display;
    }
}
