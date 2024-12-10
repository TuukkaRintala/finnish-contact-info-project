package main.java.rintalatuukka.contacts.objects;

import main.java.rintalatuukka.contacts.util.Validation;

public class Contact {
    private String id;
    private String[] firstNames = new String[1];
    private String lastName;
    private String phoneNumber;
    private String address;
    // private int zipCode;
    // private String city;
    private String email;
    public Contact() {
        
    }
    public Contact(String startId, String[] startFirstNames,
                   String startLastName, String startPhoneNumber,
                   String startAddress, String startEmail) {
        setId(startId);
        setFirstNames(startFirstNames);
        setLastName(startLastName);
        setPhoneNumber(startPhoneNumber);
        setAddress(startAddress);
        setEmail(startEmail);
    }
    public Contact(String startId, String[] startFirstNames,
                   String startLastName, String startPhoneNumber,
                   String startAddress) {
        setId(startId);
        setFirstNames(startFirstNames);
        setLastName(startLastName);
        setPhoneNumber(phoneNumber);
        setAddress(startAddress);
    }
    public Contact(String startId, String[] startFirstNames,
                   String startLastName, String startPhoneNumber) {
        setId(startId);
        setFirstNames(startFirstNames);
        setLastName(startLastName);
        setPhoneNumber(startPhoneNumber);
    }
    public String getId() {
        return ("" + id);
    }
    public void setId(String newId) {
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
    public String getAddress() {
        return (address);
    }
    public void setAddress(String newAddress) {
        if (true) {
            address = newAddress;
        }
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String newEmail) {
        if (true) {
            email = newEmail;
        }
    }
}