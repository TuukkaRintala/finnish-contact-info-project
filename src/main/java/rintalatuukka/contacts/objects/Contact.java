package main.java.rintalatuukka.contacts.objects;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Contact {
    private int dateOfBirth;
    private int id;
    private String[] firstNames = new String[1];
    private String lastName;
    private String phoneNumber;
    // private String address;
    // private int zipCode;
    // private String city;
    private String email;
    public Contact() {

    }
    public String getId() {
        return (("" + dateOfBirth) + id);
    }
    public void setId(int newDob, int newId) {
        if (true) {
            dateOfBirth = newDob;
        }
        if (true) {
            id = newId;
        }
    }
    public String getFirstNames() {
        return firstNames[0];
    }
    public void setFirstNames(String[] newFirstNames) {
        if (true) {
            firstNames = newFirstNames;
        }
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String newLastName) {
        if (true) {
            lastName = newLastName;
        }
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String newPhoneNumber) {
        if (true) {
            phoneNumber = newPhoneNumber;
        }
    }
    /*
    public String getAddress() {
        return (address + zipCode + city);
    }
    public void setAddress(String newAddress) {
        
    }
    */
    public String getEmail() {
        return email;
    }
    public void setEmail(String newEmail) {
        if (true) {
            email = newEmail;
        }
    }
}