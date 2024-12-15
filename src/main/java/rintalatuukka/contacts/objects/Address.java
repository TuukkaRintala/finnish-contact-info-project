package main.java.rintalatuukka.contacts.objects;

import main.java.rintalatuukka.contacts.objects.Info;
import main.java.rintalatuukka.contacts.util.GetInputs;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * This object contains the info for a finnish addresses and methods to
 * manipulate that data.
 */

public class Address implements Info {
    private String info = "";
    private String street = "";
    private String zipCode = "";
    private String city = "";
    public Address(String startInfo) {
        setInfo(startInfo);
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String newInfo) {
        String[] splitInfo = newInfo.split("[.]", 3);
        info = "";
        try {
            setStreet(splitInfo[0]);
            setZipCode(splitInfo[1]);
            setCity(splitInfo[2]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Address data formatted wrong. Address data " +
                "should always contain three fields divided by a full stop .");
        }
    }
    public void setStreet(String newStreet) {
        if (validateStreet(newStreet)) {
            street = newStreet;
            info += newStreet;
        } else {
            System.out.println("Invalid street address. Correct format is for" +
                               " example: Hämeenkatu 12 B4.");
        }
        info += ".";
    }
    public void setZipCode(String newZip) {
        if (validateZip(newZip)) {
            zipCode = newZip;
            info += newZip;
        } else {
            System.out.println("Invalid zip code. Correct format is for" +
                               " example: 33800.");
        }
        info += ".";
    }
    public void setCity(String newCity) {
        if (validateCity(newCity)) {
            city = newCity;
        } else {
            System.out.println("Invalid city. Correct format is for" +
                               " example: Tampere.");
        }
    }
    public boolean validateStreet(String info) {
        final String regex = "^[([A-Z]|Å|Ä|Ö)][([a-z]|å|ä|ö)+]{2,50}" +
                             "([ ]([1-9]|[1-9][0-9]){1,2}))" +
                             "([ ][A-Z]([1-9]|[0][1-9]|[1-9][0-9]){1,2})??";
        Pattern streetPattern = Pattern.compile(regex);
        Matcher streetMatcher = streetPattern.matcher(info);
        return streetMatcher.matches();
    }
    public boolean validateZip(String info) {
        final String regex = "^[0-9]{5}$";
        Pattern zipPattern = Pattern.compile(regex);
        Matcher zipMatcher = zipPattern.matcher(info);
        return zipMatcher.matches();
    }
    public boolean validateCity(String info) {
        final String regex = "^[([A-Z]|Å|Ä|Ö)][([a-z]|å|ä|ö)+]{2,50}";
        Pattern cityPattern = Pattern.compile(regex);
        Matcher cityMatcher = cityPattern.matcher(info);
        return cityMatcher.matches();
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
        return (street + " " + zipCode + " " + city);
    }
}
